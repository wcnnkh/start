package io.basc.start.bytedance.video;

import io.swagger.v3.oas.annotations.media.Schema;

public class VideoData extends VideoMetadata {
	private static final long serialVersionUID = 1L;
	@Schema(description = "表示视频状态。1:已发布;2:不适宜公开;4:审核中", example = "1")
	private Integer video_status;

	public Integer getVideo_status() {
		return video_status;
	}

	public void setVideo_status(Integer video_status) {
		this.video_status = video_status;
	}
}
