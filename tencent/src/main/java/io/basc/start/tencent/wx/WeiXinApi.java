package io.basc.start.tencent.wx;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.basc.framework.http.HttpUtils;
import io.basc.framework.http.MediaType;
import io.basc.framework.http.client.HttpClient;
import io.basc.framework.json.JSONUtils;
import io.basc.framework.json.JsonObject;
import io.basc.framework.logger.Logger;
import io.basc.framework.logger.LoggerFactory;
import io.basc.framework.retry.RetryOperations;
import io.basc.framework.retry.support.RetryTemplate;
import io.basc.framework.security.Token;
import io.basc.framework.util.Assert;
import io.basc.framework.util.stream.Processor;

public class WeiXinApi {
	private static final String CODE_NAME = "errcode";
	private static final String MSG_NAME = "errmsg";
	private static Logger logger = LoggerFactory.getLogger(WeiXinApi.class);
	private final String appid;
	private final String appsecret;
	private RetryOperations retryOperations = new RetryTemplate();
	private long expireAheadTime = 300;// token提前过期时间
	private TimeUnit expireAheadTimeUnit = TimeUnit.SECONDS;
	private HttpClient httpClient = HttpUtils.getHttpClient();
	private volatile Map<String, Token> tokenMap = new HashMap<String, Token>(8);
	private volatile Map<String, Token> ticketMap = new HashMap<String, Token>(8);

	public WeiXinApi(String appid, String appsecret) {
		this.appid = appid;
		this.appsecret = appsecret;
	}

	public final String getAppid() {
		return appid;
	}

	public final String getAppsecret() {
		return appsecret;
	}

	public RetryOperations getRetryOperations() {
		return retryOperations;
	}

	public void setRetryOperations(RetryOperations retryOperations) {
		Assert.requiredArgument(retryOperations != null, "retryOperations");
		this.retryOperations = retryOperations;
	}

	public final long getExpireAheadTime() {
		return expireAheadTime;
	}

	public final TimeUnit getExpireAheadTimeUnit() {
		return expireAheadTimeUnit;
	}

	public void setExpireAheadTime(long expireAheadTime, TimeUnit expireAheadTimeUnit) {
		Assert.requiredArgument(expireAheadTimeUnit != null, "expireAheadTimeUnit");
		this.expireAheadTime = expireAheadTime;
		this.expireAheadTimeUnit = expireAheadTimeUnit;
	}

	public final Token getClientCredential(boolean forceUpdate) throws WeiXinApiException {
		return getToken("client_credential", forceUpdate);
	}

	/**
	 * 是否是无效的
	 * 
	 * @param token
	 * @return
	 */
	protected boolean isInvalidToken(Token token) {
		return token == null || token.isExpired(expireAheadTime, expireAheadTimeUnit);
	}

	public HttpClient getHttpClient() {
		return httpClient;
	}

	public void setHttpClient(HttpClient httpClient) {
		Assert.requiredArgument(httpClient != null, "httpClient");
		this.httpClient = httpClient;
	}

	public JsonObject doGet(String url) throws WeiXinApiException {
		String content = httpClient.get(String.class, url).getBody();
		if (logger.isDebugEnabled()) {
			logger.debug("request:{}, response:{}", url, content);
		}
		JsonObject json = JSONUtils.getJsonSupport().parseObject(content);
		if (json.getLongValue(CODE_NAME) != 0) {
			throw new WeiXinApiException(json.getLongValue(CODE_NAME), json.getString(MSG_NAME));
		}
		return json;
	}

	public JsonObject doPostFormData(String url, Map<String, ?> data) {
		String content = HttpUtils.getHttpClient().post(String.class, url, data, MediaType.APPLICATION_FORM_URLENCODED)
				.getBody();
		if (logger.isDebugEnabled()) {
			logger.debug("request:{}, formData={}, response:{}", url, data, content);
		}
		JsonObject json = JSONUtils.getJsonSupport().parseObject(content);
		if (json.getLongValue(CODE_NAME) != 0) {
			throw new WeiXinApiException(json.getLongValue(CODE_NAME), json.getString(MSG_NAME));
		}
		return json;
	}

	public Token getToken(String grantType, boolean forceUpdate) throws WeiXinApiException {
		Token token = tokenMap.get(grantType);
		if (forceUpdate || isInvalidToken(token)) {
			synchronized (tokenMap) {
				token = tokenMap.get(grantType);
				if (forceUpdate || isInvalidToken(token)) {
					StringBuilder sb = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token");
					sb.append("?grant_type=").append(grantType);
					sb.append("&appid=").append(appid);
					sb.append("&secret=").append(appsecret);
					JsonObject json = doGet(sb.toString());
					token = new Token(json.getString("access_token"), json.getIntValue("expires_in"), TimeUnit.SECONDS);
					tokenMap.put(grantType, token);
				}
			}
		}
		return token;
	}

	public final <T, E extends Throwable> T processWithClientCredential(Processor<Token, T, E> processor)
			throws WeiXinApiException, E {
		return processWithToken("client_credential", processor);
	}

	public <T, E extends Throwable> T processWithToken(String grantType, Processor<Token, T, E> processor)
			throws WeiXinApiException, E {
		return getRetryOperations().execute((context) -> {
			try {
				return processor.process(getToken(grantType, context.getRetryCount() != 0));
			} catch (Throwable e) {
				if (e instanceof WeiXinApiException && ((WeiXinApiException) e).isInvalidCredential()) {
					context.setExhaustedOnly();
				}
				throw e;
			}
		});
	}

	public final Token getJsapiTicket(boolean forceUpdate) throws WeiXinApiException {
		return getTicket("jsapi", forceUpdate);
	}

	public Token getTicket(String type, boolean forceUpdate) throws WeiXinApiException {
		Token ticket = tokenMap.get(type);
		if (forceUpdate || isInvalidToken(ticket)) {
			synchronized (ticketMap) {
				ticket = ticketMap.get(type);
				if (forceUpdate || isInvalidToken(ticket)) {
					ticket = processWithClientCredential((token) -> {
						StringBuilder sb = new StringBuilder("https://api.weixin.qq.com/cgi-bin/ticket/getticket");
						sb.append("?access_token=").append(token.getToken());
						sb.append("&type=").append(type);
						JsonObject json = doGet(sb.toString());
						return new Token(json.getString("ticket"), json.getIntValue("expires_in"), TimeUnit.SECONDS);
					});
					ticketMap.put(type, ticket);
				}
			}
		}
		return ticket;
	}

	public final <T, E extends Throwable> T processWithJsapiTicket(Processor<Token, T, E> processor)
			throws WeiXinApiException, E {
		return processWithTicket("jsapi", processor);
	}

	public <T, E extends Throwable> T processWithTicket(String type, Processor<Token, T, E> processor)
			throws WeiXinApiException, E {
		return getRetryOperations().execute((context) -> {
			try {
				return processor.process(getTicket(type, context.getRetryCount() != 0));
			} catch (Throwable e) {
				if (e instanceof WeiXinApiException && ((WeiXinApiException) e).isInvalidCredential()) {
					context.setExhaustedOnly();
				}
				throw e;
			}
		});
	}

	public UserAccessToken getUserAccesstoken(String code) {
		Map<String, String> map = new HashMap<String, String>(4, 1);
		map.put("appid", appid);
		map.put("secret", appsecret);
		map.put("code", code);
		map.put("grant_type", "authorization_code");
		JsonObject json = doPostFormData("https://api.weixin.qq.com/sns/oauth2/access_token", map);
		return null;
		//return new UserAccessToken(parseAccessToken(json, "authorization_code"), json.getString("openid"));
	}
}
