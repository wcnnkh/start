package io.basc.integration.bytedance.oauth;

import io.swagger.v3.oas.annotations.media.Schema;

public class OauthRefreshTokenRequest extends OauthRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "填refresh_token", required = true)
	private String grant_type;
	@Schema(description = "填写通过access_token获取到的refresh_token参数", required = true)
	private String refresh_token;

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
}
