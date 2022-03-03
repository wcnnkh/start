package io.basc.start.tencent.wx.api;

import io.basc.framework.oauth2.AccessToken;
import io.basc.framework.security.Token;

public class UserAccessToken extends AccessToken {
	private static final long serialVersionUID = 1L;
	private final String openid;

	/**
	 * 方便序列化
	 */
	protected UserAccessToken() {
		this(null, null, null, null, null, null);
	}

	public UserAccessToken(AccessToken accessToken, String openid) {
		super(accessToken);
		this.openid = openid;
	}

	public UserAccessToken(Token accessToken, String type, Token refreshToken, String scope, String state,
			String openid) {
		super(accessToken, type, refreshToken, scope, state);
		this.openid = openid;
	}

	public String getOpenid() {
		return openid;
	}

	@Override
	public UserAccessToken clone() {
		return new UserAccessToken(super.clone(), openid);
	}
}
