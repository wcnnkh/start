package io.basc.start.bytedance.devtool;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class SandboxWebhookEventSendRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "需要mock的事件类型, 开放平台会通过webhook发送一条mock数据给你")
	private String event_type;

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}
}
