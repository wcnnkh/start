package io.basc.platform.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalItemShare extends DataExternalMetadata {
	private static final long serialVersionUID = 1L;
	@Schema(description = "每日分享数", example = "200")
	private Long share;

	public Long getShare() {
		return share;
	}

	public void setShare(Long share) {
		this.share = share;
	}

}
