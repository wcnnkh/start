package scw.integration.tencent.wx.miniprogram;

import java.util.Collections;
import java.util.List;

import scw.json.JsonArray;
import scw.json.JsonObject;

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
