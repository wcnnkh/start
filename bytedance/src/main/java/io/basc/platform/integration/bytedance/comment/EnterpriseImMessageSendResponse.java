package io.basc.platform.integration.bytedance.comment;

import io.basc.platform.integration.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class EnterpriseImMessageSendResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "内部使用")
	private String server_msg_id;

	public String getServer_msg_id() {
		return server_msg_id;
	}

	public void setServer_msg_id(String server_msg_id) {
		this.server_msg_id = server_msg_id;
	}
}
