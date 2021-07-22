package scw.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalUserShare extends DataExternalMetadata {
	private static final long serialVersionUID = 1L;
	@Schema(description = "新增分享", example = "200")
	private Long new_share;

	public Long getNew_share() {
		return new_share;
	}

	public void setNew_share(Long new_share) {
		this.new_share = new_share;
	}
}
