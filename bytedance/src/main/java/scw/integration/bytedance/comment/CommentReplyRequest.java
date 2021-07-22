package scw.integration.bytedance.comment;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class CommentReplyRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "需要回复的评论id（如果需要回复的是视频不传此字段）")
	private String comment_id;
	@Schema(description = "评论内容")
	private String content;
	@Schema(description = "视频id", required = true)
	@NotNull
	private String item_id;

	public String getComment_id() {
		return comment_id;
	}

	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
}
