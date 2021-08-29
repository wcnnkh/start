package io.basc.integration.bytedance.video;

import io.basc.integration.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class VideoPartInitResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "上传id", example = "@8hxdhauTCM")
	private String upload_id;

	public String getUpload_id() {
		return upload_id;
	}

	public void setUpload_id(String upload_id) {
		this.upload_id = upload_id;
	}
}
