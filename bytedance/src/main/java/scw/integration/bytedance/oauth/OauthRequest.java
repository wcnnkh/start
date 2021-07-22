package scw.integration.bytedance.oauth;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class OauthRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "应用唯一标识", required = true)
	@NotNull
	private String client_key;

	public String getClient_key() {
		return client_key;
	}

	public void setClient_key(String client_key) {
		this.client_key = client_key;
	}
}
