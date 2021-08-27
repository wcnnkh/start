package io.basc.platform.integration.bytedance.video;

import io.basc.platform.integration.bytedance.oauth.ClientPagingRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class PoiSearchKeywordRequest extends ClientPagingRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "查询关键字，例如美食", required = true)
	private String keyword;
	@Schema(description = "查询城市，例如上海、北京")
	private String city;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
