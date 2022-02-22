package io.basc.start.tencent.wx;

import io.basc.framework.data.TemporaryStorage;
import io.basc.framework.oauth2.AccessToken;
import io.basc.framework.security.Token;

public class TemporaryCacheTokenFactory extends DefaultTokenFactory {
	private final TemporaryStorage temporaryCache;

	public TemporaryCacheTokenFactory(String appId, String appSecret, TemporaryStorage temporaryCache) {
		super(appId, appSecret);
		this.temporaryCache = temporaryCache;
	}

	@Override
	public AccessToken getAccessToken(String type, boolean forceUpdate) {
		String key = "wx-token:" + getAppId() + ":" + type;
		AccessToken accessToken = temporaryCache.get(key);
		if (accessToken == null || forceUpdate || accessToken.getToken().isExpired(getTokenExpireAheadTime())) {
			accessToken = super.getAccessToken(type, forceUpdate);
			temporaryCache.set(key, accessToken.getToken().getExpiresIn() - getTokenExpireAheadTime(), accessToken);
		}
		return accessToken;
	}

	@Override
	public Token getTicket(String type, boolean forceUpdate) {
		String key = "wx-ticket:" + getAppId() + ":" + type;
		Token token = temporaryCache.get(key);
		if (token == null || forceUpdate || token.isExpired(getTokenExpireAheadTime())) {
			token = super.getTicket(type, forceUpdate);
			temporaryCache.set(key, token.getExpiresIn() - getTokenExpireAheadTime(), token);
		}
		return token;
	}
}
