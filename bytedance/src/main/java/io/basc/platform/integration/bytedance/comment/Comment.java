package io.basc.platform.integration.bytedance.comment;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "评论id")
	private String comment_id;
	@Schema(description = "该条评论发布者的user_id")
	private String comment_user_id;
	@Schema(description = "评论的具体内容")
	private String content;
	@Schema(description = "创建时间戳，单位为秒")
	private Long create_time;
	@Schema(description = "该评论的点赞数")
	private Integer digg_count;
	@Schema(description = "	该评论的回复数量")
	private Integer reply_comment_total;
	@Schema(description = "该评论是否被置顶")
	private Boolean top;

	public String getComment_id() {
		return comment_id;
	}

	public void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}

	public String getComment_user_id() {
		return comment_user_id;
	}

	public void setComment_user_id(String comment_user_id) {
		this.comment_user_id = comment_user_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public Integer getDigg_count() {
		return digg_count;
	}

	public void setDigg_count(Integer digg_count) {
		this.digg_count = digg_count;
	}

	public Integer getReply_comment_total() {
		return reply_comment_total;
	}

	public void setReply_comment_total(Integer reply_comment_total) {
		this.reply_comment_total = reply_comment_total;
	}

	public Boolean getTop() {
		return top;
	}

	public void setTop(Boolean top) {
		this.top = top;
	}
}
