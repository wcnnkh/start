package io.basc.start.tencent.wx.api;

import io.basc.framework.json.JsonObject;
import io.basc.framework.json.JsonObjectWrapper;

public class ApiResponse extends JsonObjectWrapper {
	public ApiResponse(JsonObject target) {
		super(target);
	}

	public int getErrcode() {
		return getIntValue("errcode");
	}

	public String getErrmsg() {
		return getString("errmsg");
	}

	public boolean isSuccess() {
		return getErrcode() == 0;
	}

	public boolean isError() {
		return !isSuccess();
	}
}
