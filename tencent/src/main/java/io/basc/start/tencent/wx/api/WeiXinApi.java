package io.basc.start.tencent.wx.api;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
	private RetryOperations retryOperations = RetryTemplate.DEFAULT;
	private long expireAheadTime = 300;// token提前过期时间
	private TimeUnit expireAheadTimeUnit = TimeUnit.SECONDS;
	private HttpClient httpClient = HttpUtils.getHttpClient();
	private volatile Map<String, Token> tokenMap = new HashMap<String, Token>(4);
	private volatile Map<String, Token> ticketMap = new HashMap<String, Token>(4);

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

	protected JsonObject parseJson(String response) {
		JsonObject json = JSONUtils.getJsonSupport().parseObject(response);
		if (json.getLongValue(CODE_NAME) != 0) {
			throw new WeiXinApiException(json.getLongValue(CODE_NAME), json.getString(MSG_NAME));
		}
		return json;
	}

	public JsonObject doGet(String url) throws WeiXinApiException {
		String content = getHttpClient().get(String.class, url).getBody();
		if (logger.isDebugEnabled()) {
			logger.debug("request:{}, response:{}", url, content);
		}
		return parseJson(content);
	}

	public JsonObject doPost(String url, Object body, MediaType contentType) {
		String content = getHttpClient().post(String.class, url, body, contentType).getBody();
		if (logger.isDebugEnabled()) {
			logger.debug("request:{}, formData={}, response:{}", url, body, content);
		}
		return parseJson(content);
	}

	/**
	 * 直接从服务器获取
	 * 
	 * @param appid
	 * @param appsecret
	 * @param grantType
	 * @return
	 * @throws WeiXinApiException
	 */
	public Token getToken(String appid, String appsecret, String grantType) throws WeiXinApiException {
		StringBuilder sb = new StringBuilder("https://api.weixin.qq.com/cgi-bin/token");
		sb.append("?grant_type=").append(grantType);
		sb.append("&appid=").append(appid);
		sb.append("&secret=").append(appsecret);
		JsonObject json = doGet(sb.toString());
		return new Token(json.getString("access_token"), json.getIntValue("expires_in"), TimeUnit.SECONDS);
	}

	/**
	 * 直接从服务器获取
	 * 
	 * @param grantType
	 * @return
	 * @throws WeiXinApiException
	 */
	public final Token getToken(String grantType) throws WeiXinApiException {
		return getToken(this.appid, this.appsecret, grantType);
	}

	public Token getToken(String grantType, boolean forceUpdate) throws WeiXinApiException {
		Token token = tokenMap.get(grantType);
		if (forceUpdate || isInvalidToken(token)) {
			synchronized (tokenMap) {
				token = tokenMap.get(grantType);
				if (forceUpdate || isInvalidToken(token)) {
					token = getToken(grantType);
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

	public final <T, E extends Throwable> T processWithToken(String grantType, Processor<Token, T, E> processor)
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

	public ApiDomainIp getApiDomainIp(String accessToken) {
		JsonObject json = doGet("https://api.weixin.qq.com/cgi-bin/get_api_domain_ip?access_token=" + accessToken);
		return new ApiDomainIp(
				json.getJsonArray("ip_list").stream().map((e) -> e.getAsString()).collect(Collectors.toList()));
	}

	public final ApiDomainIp getApiDomainIp() {
		return processWithClientCredential((token) -> getApiDomainIp(token.getToken()));
	}

	/**
	 * 直接从服务器获取
	 * 
	 * @param access_token
	 * @param type
	 * @return
	 * @throws WeiXinApiException
	 */
	public Token getTicket(String access_token, String type) throws WeiXinApiException {
		StringBuilder sb = new StringBuilder("https://api.weixin.qq.com/cgi-bin/ticket/getticket");
		sb.append("?access_token=").append(access_token);
		sb.append("&type=").append(type);
		JsonObject json = doGet(sb.toString());
		return new Token(json.getString("ticket"), json.getIntValue("expires_in"), TimeUnit.SECONDS);
	}

	public Token getTicket(String type, boolean forceUpdate) throws WeiXinApiException {
		Token ticket = tokenMap.get(type);
		if (forceUpdate || isInvalidToken(ticket)) {
			synchronized (ticketMap) {
				ticket = ticketMap.get(type);
				if (forceUpdate || isInvalidToken(ticket)) {
					ticket = processWithClientCredential((token) -> {
						return getTicket(token.getToken(), type);
					});
					ticketMap.put(type, ticket);
				}
			}
		}
		return ticket;
	}

	public final <T, E extends Throwable> T processWithTicket(String type, Processor<Token, T, E> processor)
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

}
