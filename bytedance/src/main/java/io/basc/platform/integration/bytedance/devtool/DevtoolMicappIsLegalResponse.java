package io.basc.platform.integration.bytedance.devtool;

import io.basc.platform.integration.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class DevtoolMicappIsLegalResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "是否合法的信息")
	private Boolean is_legal;

	public Boolean getIs_legal() {
		return is_legal;
	}

	public void setIs_legal(Boolean is_legal) {
		this.is_legal = is_legal;
	}
}
