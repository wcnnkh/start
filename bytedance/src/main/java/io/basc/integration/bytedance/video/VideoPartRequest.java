package io.basc.integration.bytedance.video;

import io.basc.integration.bytedance.user.UserRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class VideoPartRequest extends UserRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "分片上传的标记。有限时间为2小时", example = "@8hxdhauTCM", required = true)
	private String upload_id;

	public String getUpload_id() {
		return upload_id;
	}

	public void setUpload_id(String upload_id) {
		this.upload_id = upload_id;
	}
}
