package io.basc.integration.bytedance.oauth;

import io.basc.integration.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class OauthClientTokenResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "access_token接口调用凭证超时时间，单位（秒)", example = "7200")
	private Long expires_in;
	@Schema(description = "接口调用凭证", example = "access_token")
	private String access_token;

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
}
