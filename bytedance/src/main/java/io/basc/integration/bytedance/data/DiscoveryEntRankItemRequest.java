package io.basc.integration.bytedance.data;

import javax.validation.constraints.NotNull;

import io.basc.integration.bytedance.oauth.ClientRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class DiscoveryEntRankItemRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "榜单类型： * 1 - 电影 * 2 - 电视剧 * 3 - 综艺", required = true)
	@NotNull
	private Integer type;
	@Schema(description = "榜单版本：空值默认为本周榜单")
	private Integer version;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
}
