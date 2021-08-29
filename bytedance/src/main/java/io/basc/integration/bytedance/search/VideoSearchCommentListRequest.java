package io.basc.integration.bytedance.search;

import javax.validation.constraints.NotNull;

import io.basc.integration.bytedance.oauth.ClientPagingRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class VideoSearchCommentListRequest extends ClientPagingRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "视频搜索接口返回的加密的视频id", required = true)
	@NotNull
	private String sec_item_id;

	public String getSec_item_id() {
		return sec_item_id;
	}

	public void setSec_item_id(String sec_item_id) {
		this.sec_item_id = sec_item_id;
	}
}
