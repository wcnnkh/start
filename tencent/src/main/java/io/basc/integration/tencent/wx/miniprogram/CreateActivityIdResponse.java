package io.basc.integration.tencent.wx.miniprogram;

import io.basc.framework.json.JsonObject;

public final class CreateActivityIdResponse extends BaseResponse {

	public CreateActivityIdResponse(JsonObject json) {
		super(json);
	}

	public String getActivityId() {
		return getString("activity_id");
	}

	public long getExpirationTime() {
		return getLongValue("expiration_time");
	}
}
