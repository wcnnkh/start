package io.basc.integration.tencent.wx.miniprogram;

import io.basc.framework.json.JsonObject;

public final class Session extends BaseResponse {

	public Session(JsonObject json) {
		super(json);
	}

	public String getOpenid() {
		return getString("openid");
	}

	public String getSession_key() {
		return getString("session_key");
	}

	public String getUnionid() {
		return getString("unionid");
	}
}
