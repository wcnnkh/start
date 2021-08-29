package io.basc.integration.bytedance.comment;

import io.swagger.v3.oas.annotations.media.Schema;

public class CommentReplyListRequest extends CommentListRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "评论id")
	private String comment_id;

	public String getComment_id() {
		return comment_id;
	}

	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
}
