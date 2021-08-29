package io.basc.integration.bytedance.oauth;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class OauthRenewRefreshTokenRequest extends OauthRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "填写通过access_token获取到的refresh_token参数", required = true)
	@NotNull
	private String refresh_token;

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
}
