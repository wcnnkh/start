package io.basc.platform.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class Highlight implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "介绍，字符串长度<=5")
	private String content;
	@Schema(description = "优先级，数字越小优先级越高")
	private Long priority;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getPriority() {
		return priority;
	}

	public void setPriority(Long priority) {
		this.priority = priority;
	}

}
