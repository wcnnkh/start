package io.basc.start.tencent.wx.api;

import io.basc.framework.json.JsonObject;

public final class CreateActivityIdResponse extends ApiResponse {

	public CreateActivityIdResponse(JsonObject json) {
		super(json);
	}

	public String getActivityId() {
		return getAsString("activity_id");
	}

	public long getExpirationTime() {
		return getAsLong("expiration_time");
	}
}
