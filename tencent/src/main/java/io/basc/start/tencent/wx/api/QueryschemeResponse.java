package io.basc.start.tencent.wx.api;

import io.basc.framework.json.JsonObject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QueryschemeResponse {
	private SchemeQuota schemeQuota;
	private SchemeInfo schemeInfo;

	public QueryschemeResponse(JsonObject json) {
		this.schemeInfo = json.containsKey("scheme_info") ? new SchemeInfo(json.getJsonObject("scheme_info")) : null;
		this.schemeQuota = json.containsKey("scheme_quota") ? new SchemeQuota(json.getJsonObject("scheme_quota"))
				: null;
	}
}
