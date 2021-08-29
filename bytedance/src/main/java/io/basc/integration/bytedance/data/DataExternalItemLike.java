package io.basc.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalItemLike extends DataExternalMetadata {
	private static final long serialVersionUID = 1L;
	@Schema(description = "每日点赞数", example = "200")
	private Long like;

	public Long getLike() {
		return like;
	}

	public void setLike(Long like) {
		this.like = like;
	}
}
