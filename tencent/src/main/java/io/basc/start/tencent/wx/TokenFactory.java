package io.basc.start.tencent.wx;

import io.basc.framework.oauth2.AccessToken;
import io.basc.framework.security.Token;

public interface TokenFactory extends WXConstants{
	String getAppId();

	String getAppSecret();

	AccessToken getAccessToken();

	AccessToken getAccessToken(String type);

	Token getJsApiTicket();

	Token getTicket(String type);
}
