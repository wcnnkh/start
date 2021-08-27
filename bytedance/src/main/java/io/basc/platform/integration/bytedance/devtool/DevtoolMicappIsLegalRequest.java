package io.basc.platform.integration.bytedance.devtool;

import javax.validation.constraints.NotNull;

import io.basc.platform.integration.bytedance.oauth.ClientRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class DevtoolMicappIsLegalRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "输入小程序的micapp_id", example = "tt5daf2b12c2857910", required = true)
	@NotNull
	private String micapp_id;

	public String getMicapp_id() {
		return micapp_id;
	}

	public void setMicapp_id(String micapp_id) {
		this.micapp_id = micapp_id;
	}
}
