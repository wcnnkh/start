package io.basc.integration.bytedance.comment;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class EnterpriseImMessageSendRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "内部使用")
	private String client_msg_id;
	@Schema(description = "	消息体", example = "{\\\"text\\\": \\\"文本内容\\\"}", required = true)
	private ImMessageContent content;
	@Schema(description = "消息内容格式:\"text\"(文本消息) \"image\"(图片消息) \"video\"(视频消息) \"card\"(卡片消息)", example = "text", required = true)
	private String message_type;
	@Schema(description = "客服id，传则走客服会话，否则为普通会话")
	private String persona_id;
	@Schema(description = "消息的接收方openid")
	private String to_user_id;

	public String getClient_msg_id() {
		return client_msg_id;
	}

	public void setClient_msg_id(String client_msg_id) {
		this.client_msg_id = client_msg_id;
	}

	public ImMessageContent getContent() {
		return content;
	}

	public void setContent(ImMessageContent content) {
		this.content = content;
	}

	public String getMessage_type() {
		return message_type;
	}

	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}

	public String getPersona_id() {
		return persona_id;
	}

	public void setPersona_id(String persona_id) {
		this.persona_id = persona_id;
	}

	public String getTo_user_id() {
		return to_user_id;
	}

	public void setTo_user_id(String to_user_id) {
		this.to_user_id = to_user_id;
	}
}
