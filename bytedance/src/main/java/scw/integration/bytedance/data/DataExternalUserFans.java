package scw.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalUserFans extends DataExternalMetadata {
	private static final long serialVersionUID = 1L;
	@Schema(description = "每日总粉丝数", example = "200")
	private Long total_fans;
	@Schema(description = "每天新粉丝数", example = "200")
	private Long new_fans;

	public Long getTotal_fans() {
		return total_fans;
	}

	public void setTotal_fans(Long total_fans) {
		this.total_fans = total_fans;
	}

	public Long getNew_fans() {
		return new_fans;
	}

	public void setNew_fans(Long new_fans) {
		this.new_fans = new_fans;
	}
}
