package io.basc.start.tencent.wx;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.oauth2.AccessToken;
import io.basc.framework.retry.RetryOperations;
import io.basc.framework.retry.support.RetryTemplate;
import io.basc.framework.security.Token;

@Provider
public class DefaultTokenFactory implements TokenFactory {
	private final String appId;
	private final String appSecret;
	private volatile Map<String, AccessToken> accessTokenMap = new HashMap<String, AccessToken>(8);
	private volatile Map<String, Token> ticketMap = new HashMap<String, Token>(8);
	private int tokenExpireAheadTime = 60;// token提前过期时间
	private RetryOperations retryOperations = new RetryTemplate();

	public DefaultTokenFactory(String appId, String appSecret) {
		this.appId = appId;
		this.appSecret = appSecret;
	}

	public final String getAppId() {
		return appId;
	}

	public final String getAppSecret() {
		return appSecret;
	}

	public RetryOperations getRetryOperations() {
		return retryOperations;
	}

	public void setRetryOperations(RetryOperations retryOperations) {
		this.retryOperations = retryOperations;
	}

	/**
	 * token提前过期时间(秒)
	 * 
	 * @return
	 */
	public final int getTokenExpireAheadTime() {
		return tokenExpireAheadTime;
	}

	public void setTokenExpireAheadTime(int tokenExpireAheadTime) {
		this.tokenExpireAheadTime = tokenExpireAheadTime;
	}

	@Override
	public final AccessToken getAccessToken(boolean forceUpdate) {
		return getAccessToken("client_credential", forceUpdate);
	}

	@Override
	public AccessToken getAccessToken(String type, boolean forceUpdate) {
		AccessToken accessToken = accessTokenMap.get(type);
		if (accessToken == null || forceUpdate
				|| accessToken.getToken().isExpired(tokenExpireAheadTime, TimeUnit.SECONDS)) {
			synchronized (accessTokenMap) {
				accessToken = accessTokenMap.get(type);
				if (accessToken == null || accessToken.getToken().isExpired(tokenExpireAheadTime, TimeUnit.SECONDS)) {
					accessToken = WeiXinUtils.getAccessToken(type, appId, appSecret);
					accessTokenMap.put(type, accessToken);
				}
			}
		}
		return accessToken;
	}

	@Override
	public final Token getJsApiTicket(boolean forceUpdate) {
		return getTicket("jsapi", forceUpdate);
	}

	@Override
	public Token getTicket(String type, boolean forceUpdate) {
		Token token = ticketMap.get(type);
		if (token == null || forceUpdate || token.isExpired(tokenExpireAheadTime, TimeUnit.SECONDS)) {
			synchronized (ticketMap) {
				token = ticketMap.get(type);
				if (token == null || token.isExpired(tokenExpireAheadTime, TimeUnit.SECONDS)) {
					token = WeiXinUtils.getTicket(getAccessToken().getToken().getToken(), type);
					ticketMap.put(type, token);
				}
			}
		}
		return token;
	}
}
