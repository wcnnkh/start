package io.basc.start.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalUserProfile extends DataExternalMetadata {
	private static final long serialVersionUID = 1L;
	@Schema(description = "当日个人主页访问人数", example = "200")
	private Long profile_uv;

	public Long getProfile_uv() {
		return profile_uv;
	}

	public void setProfile_uv(Long profile_uv) {
		this.profile_uv = profile_uv;
	}
}
