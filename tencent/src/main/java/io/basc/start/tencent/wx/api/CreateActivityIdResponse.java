package io.basc.start.tencent.wx.api;

import io.basc.framework.json.JsonObject;

public final class CreateActivityIdResponse extends ApiResponse {

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
