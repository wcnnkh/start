package io.basc.start.tencent.wx.miniprogram;

import io.basc.framework.json.JsonArray;
import io.basc.framework.json.JsonObject;

import java.util.Collections;
import java.util.List;

public final class GetTemplateLibraryByIdResponse extends BaseResponse {

	public GetTemplateLibraryByIdResponse(JsonObject json) {
		super(json);
	}

	/**
	 * 模板标题 id
	 * 
	 * @return
	 */
	public String getId() {
		return getString("id");
	}

	/**
	 * 模板标题
	 * 
	 * @return
	 */
	public String getTitle() {
		return getString("title");
	}

	/**
	 * 关键词列表
	 * 
	 * @return
	 */
	public List<Keyword> getKeywordList() {
		JsonArray jsonArray = getJsonArray("keyword_list");
		if(jsonArray == null) {
			return Collections.emptyList();
		}
		return jsonArray.convert(Keyword.class);
	}
}
