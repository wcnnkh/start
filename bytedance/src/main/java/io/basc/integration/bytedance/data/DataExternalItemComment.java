package io.basc.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalItemComment extends DataExternalMetadata {
	private static final long serialVersionUID = 1L;
	@Schema(description = "每日评论数", example = "200")
	private Long comment;

	public Long getComment() {
		return comment;
	}

	public void setComment(Long comment) {
		this.comment = comment;
	}
}
