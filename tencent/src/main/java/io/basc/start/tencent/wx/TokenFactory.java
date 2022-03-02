package io.basc.start.tencent.wx;

import io.basc.framework.oauth2.AccessToken;
import io.basc.framework.security.InvalidTokenException;
import io.basc.framework.security.Token;

public interface TokenFactory {
	String getAppId();

	String getAppSecret();

	default AccessToken getAccessToken() throws InvalidTokenException {
		return getAccessToken(false);
	}

	AccessToken getAccessToken(boolean forceUpdate);

	default AccessToken getAccessToken(String type) {
		return getAccessToken(type, false);
	}

	AccessToken getAccessToken(String type, boolean forceUpdate);

	default Token getJsApiTicket() throws InvalidTokenException {
		return getJsApiTicket(false);
	}

	Token getJsApiTicket(boolean forceUpdate);

	default Token getTicket(String type) throws InvalidTokenException {
		return getTicket(type, false);
	}

	Token getTicket(String type, boolean forceUpdate) throws InvalidTokenException;
}
