package io.basc.platform.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalAnchorMpItemClickDistributionRequest extends DataExternalRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "mp_id，小程序id")
	private String mp_id;

	public String getMp_id() {
		return mp_id;
	}

	public void setMp_id(String mp_id) {
		this.mp_id = mp_id;
	}
}
