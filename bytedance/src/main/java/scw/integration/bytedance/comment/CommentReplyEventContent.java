package scw.integration.bytedance.comment;

import java.io.Serializable;

public class CommentReplyEventContent implements Serializable {
	private static final long serialVersionUID = 1L;
	private String comment_id;
	private String comment_user_id;
	private String content;
	private Long create_time;
	private Integer digg_count;
	private Integer reply_comment_total;

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
}
