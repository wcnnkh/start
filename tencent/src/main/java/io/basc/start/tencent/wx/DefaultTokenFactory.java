package io.basc.start.tencent.wx;

import java.util.concurrent.ConcurrentHashMap;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.oauth2.AccessToken;
import io.basc.framework.security.Token;

@Provider
public class DefaultTokenFactory implements TokenFactory {
	private final String appId;
	private final String appSecret;
	private final ConcurrentHashMap<String, AccessToken> accessTokenMap = new ConcurrentHashMap<String, AccessToken>(8);
	private final ConcurrentHashMap<String, Token> ticketMap = new ConcurrentHashMap<String, Token>(8);
	private int tokenExpireAheadTime = 60;// token提前过期时间

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

	public final AccessToken getAccessToken() {
		return getAccessToken("client_credential");
	}

	public AccessToken getAccessToken(String type) {
		AccessToken accessToken = accessTokenMap.get(type);
		if (accessToken == null || accessToken.getToken().isExpired(tokenExpireAheadTime)) {
			accessToken = WeiXinUtils.getAccessToken(type, appId, appSecret);
			accessTokenMap.putIfAbsent(type, accessToken);
		}
		return accessToken;
	}

	public final Token getJsApiTicket() {
		return getTicket("jsapi");
	}

	public Token getTicket(String type) {
		Token token = ticketMap.get(type);
		if (token == null || token.isExpired(tokenExpireAheadTime)) {
			token = WeiXinUtils.getTicket(getAccessToken().getToken().getToken(), type);
			ticketMap.put(type, token);
		}
		return token;
	}
}