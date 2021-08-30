package io.basc.start.tencent.wx.miniprogram;

import io.basc.framework.json.JsonArray;
import io.basc.framework.json.JsonObject;

import java.util.Collections;
import java.util.List;

public final class GetTemplateListResponse extends BaseResponse {

	GetTemplateListResponse() {
		super(null);
	}

	public GetTemplateListResponse(JsonObject json) {
		super(json);
	}

	public List<Template> getList() {
		JsonArray jsonArray = getJsonArray("list");
		if(jsonArray == null) {
			return Collections.emptyList();
		}
		return jsonArray.convert(Template.class);
	}
}
