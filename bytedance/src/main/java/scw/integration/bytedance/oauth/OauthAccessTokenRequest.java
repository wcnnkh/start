package scw.integration.bytedance.oauth;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class OauthAccessTokenRequest extends OauthRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "应用唯一标识对应的密钥", required = true)
	@NotNull
	private String client_secret;
	@Schema(description = "	授权码", required = true)
	private String code;
	@Schema(description = "写死\"authorization_code\"即可", required = true)
	private String grant_type = "authorization_code";

	public String getClient_secret() {
		return client_secret;
	}

	public void setClient_secret(String client_secret) {
		this.client_secret = client_secret;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getGrant_type() {
		return grant_type;
	}

	public void setGrant_type(String grant_type) {
		this.grant_type = grant_type;
	}
}
