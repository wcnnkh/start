package io.basc.integration.tencent.wx.miniprogram;

import io.basc.framework.json.JsonObject;
import io.basc.framework.json.JsonObjectWrapper;

public class BaseResponse extends JsonObjectWrapper {
	public BaseResponse(JsonObject target) {
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
