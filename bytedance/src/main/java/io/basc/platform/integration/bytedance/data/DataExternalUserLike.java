package io.basc.platform.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalUserLike extends DataExternalMetadata {
	private static final long serialVersionUID = 1L;
	@Schema(description = "新增点赞", example = "200")
	private Long new_like;

	public Long getNew_like() {
		return new_like;
	}

	public void setNew_like(Long new_like) {
		this.new_like = new_like;
	}
}
