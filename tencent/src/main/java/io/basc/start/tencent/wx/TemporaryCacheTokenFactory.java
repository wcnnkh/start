package io.basc.start.tencent.wx;

import java.util.concurrent.TimeUnit;

import io.basc.framework.data.TemporaryStorageOperations;
import io.basc.framework.oauth2.AccessToken;
import io.basc.framework.security.Token;

public class TemporaryCacheTokenFactory extends DefaultTokenFactory {
	private final TemporaryStorageOperations storageOperations;

	public TemporaryCacheTokenFactory(String appId, String appSecret, TemporaryStorageOperations storageOperations) {
		super(appId, appSecret);
		this.storageOperations = storageOperations;
	}

	@Override
	public AccessToken getAccessToken(String type, boolean forceUpdate) {
		String key = "wx-token:" + getAppId() + ":" + type;
		AccessToken accessToken = storageOperations.get(AccessToken.class, key);
		if (accessToken == null || forceUpdate
				|| accessToken.getToken().isExpired(getTokenExpireAheadTime(), TimeUnit.SECONDS)) {
			// 未加锁
			accessToken = super.getAccessToken(type, forceUpdate);
			storageOperations.set(key, accessToken, accessToken.getToken().getPeriodOfValidity(),
					TimeUnit.MILLISECONDS);
		}
		return accessToken;
	}

	@Override
	public Token getTicket(String type, boolean forceUpdate) {
		String key = "wx-ticket:" + getAppId() + ":" + type;
		Token token = storageOperations.get(Token.class, key);
		if (token == null || forceUpdate || token.isExpired(getTokenExpireAheadTime(), TimeUnit.SECONDS)) {
			// 未加锁
			token = super.getTicket(type, forceUpdate);
			storageOperations.set(key, token, token.getPeriodOfValidity(), TimeUnit.MILLISECONDS);
		}
		return token;
	}
}
