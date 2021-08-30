package io.basc.start.bytedance.devtool;

import io.basc.start.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class JsGetticketResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "access_token接口调用凭证超时时间，单位（秒)", example = "7200")
	private Long expires_in;
	@Schema(description = "js接口调用凭证", example = "bxLdikRXVbTPdHSM05e5u5sUoXNKd8")
	private String ticket;

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
}
