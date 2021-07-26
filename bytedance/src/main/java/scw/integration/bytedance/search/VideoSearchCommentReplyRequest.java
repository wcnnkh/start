package scw.integration.bytedance.search;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class VideoSearchCommentReplyRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "视频搜索接口返回的加密的视频id", required = true)
	@NotNull
	private String sec_item_id;
	@Schema(description = "需要回复的评论id（如果需要回复的是视频不传此字段）")
	private String comment_id;
	@Schema(description = "	评论内容", required = true)
	@NotNull
	private String content;

	public String getSec_item_id() {
		return sec_item_id;
	}

	public void setSec_item_id(String sec_item_id) {
		this.sec_item_id = sec_item_id;
	}

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
}
