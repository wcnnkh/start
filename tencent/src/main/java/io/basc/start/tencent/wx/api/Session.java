package io.basc.start.tencent.wx.api;

import io.basc.framework.json.JsonObject;

public final class Session extends ApiResponse {

	public Session(JsonObject json) {
		super(json);
	}

	public String getOpenid() {
		return getAsString("openid");
	}

	public String getSession_key() {
		return getAsString("session_key");
	}

	public String getUnionid() {
		return getAsString("unionid");
	}
}
