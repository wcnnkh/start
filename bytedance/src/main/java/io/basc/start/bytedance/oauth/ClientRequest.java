package io.basc.start.bytedance.oauth;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class ClientRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "调用/oauth/client_token/生成的token，此token不需要用户授权。", required = true)
	@NotNull
	private String access_token;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
}
