package io.basc.platform.integration.bytedance.comment;

import io.basc.platform.integration.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class CommentReplyResponse extends ResponseCode {
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
