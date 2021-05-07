package scw.integration.tencent.wx.miniprogram;

import java.util.Collections;
import java.util.List;

import scw.json.JsonArray;
import scw.json.JsonObject;

public final class GetTemplateLibraryListResponse extends BaseResponse {

	GetTemplateLibraryListResponse(JsonObject json) {
		super(json);
	}

	public List<TemplateLibrary> getList() {
		JsonArray jsonArray = getJsonArray("list");
		if(jsonArray == null) {
			return Collections.emptyList();
		}
		return jsonArray.convert(TemplateLibrary.class);
	}

	public int getTotal_count() {
		return getIntValue("total_count");
	}
}
