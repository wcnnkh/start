package io.basc.platform.integration.bytedance.data;

import io.basc.platform.integration.bytedance.oauth.ClientPagingRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class DiscoveryEntRrankVersionRequest extends ClientPagingRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "榜单类型： * 1 - 电影 * 2 - 电视剧 * 3 - 综艺", example = "1")
	private Integer type;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
