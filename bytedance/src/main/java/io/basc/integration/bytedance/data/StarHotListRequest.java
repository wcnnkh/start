package io.basc.integration.bytedance.data;

import javax.validation.constraints.NotNull;

import io.basc.integration.bytedance.oauth.ClientRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class StarHotListRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "达人热榜类型 * `1` - 星图指数榜 * `2` - 涨粉指数榜 * `3` - 性价比指数榜 * `4` - 种草指数榜 * `5` - 精选指数榜 * `6` - 传播指数榜", example = "1", required = true)
	@NotNull
	private Long hot_list_type;

	public Long getHot_list_type() {
		return hot_list_type;
	}

	public void setHot_list_type(Long hot_list_type) {
		this.hot_list_type = hot_list_type;
	}
}
