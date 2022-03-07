package io.basc.start.tencent.wx.api;

import io.basc.framework.json.JsonObject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class QueryUrllinkResponse {
	private UrlLinkInfo urlLinkInfo;
	private UrlLinkQuota urlLinkQuota;

	public QueryUrllinkResponse(JsonObject json) {
		this.urlLinkInfo = json.containsKey("url_link_info") ? new UrlLinkInfo(json.getJsonObject("url_link_info"))
				: null;
		this.urlLinkQuota = json.containsKey("url_link_quota") ? new UrlLinkQuota(json.getJsonObject("url_link_quota"))
				: null;
	}
}
