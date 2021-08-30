package io.basc.start.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalUserComment extends DataExternalMetadata {
	private static final long serialVersionUID = 1L;
	@Schema(description = "新增评论", example = "200")
	private Long new_comment;

	public Long getNew_comment() {
		return new_comment;
	}

	public void setNew_comment(Long new_comment) {
		this.new_comment = new_comment;
	}
}
