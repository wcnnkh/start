package io.basc.integration.bytedance.enterprise;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class Card implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "审核不通过理由")
	private String reject_reason;
	@Schema(description = "审核状态（avaliable可使用,review审核中,reject审核不通过)")
	private String status;
	@Schema(description = "卡片id（已废弃)，跟card_name字段相同")
	private String card_id;
	@Schema(description = "卡片名称")
	private String card_name;
	@Schema(description = "卡片类型 \"question_list\"(问题列表)")
	private String card_type;
	@Schema(description = "卡片内容")
	private String content;
	@Schema(description = "卡片id")
	private String id;

	public String getReject_reason() {
		return reject_reason;
	}

	public void setReject_reason(String reject_reason) {
		this.reject_reason = reject_reason;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
