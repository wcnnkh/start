package io.basc.integration.bytedance.search;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class VideoSearchCommentReplyListRequest extends VideoSearchCommentListRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "评论id", required = true)
	@NotNull
	private String comment_id;

	public String getComment_id() {
		return comment_id;
	}

	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
}
