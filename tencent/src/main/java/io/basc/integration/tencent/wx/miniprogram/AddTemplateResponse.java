package io.basc.integration.tencent.wx.miniprogram;

import io.basc.framework.json.JsonObject;

public final class AddTemplateResponse extends BaseResponse {

	public AddTemplateResponse(JsonObject json) {
		super(json);
	}

	public String getTemplateId() {
		return getString("template_id");
	}
}