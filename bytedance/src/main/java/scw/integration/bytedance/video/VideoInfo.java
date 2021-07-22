package scw.integration.bytedance.video;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class VideoInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(example = "1280")
	private Long height;
	@Schema(description = "视频文件id。", example = "v0200f450000bn8c6aa0ifki8fikg1b0")
	private String video_id;
	@Schema(example = "720")
	private Long width;

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public String getVideo_id() {
		return video_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	public Long getWidth() {
		return width;
	}

	public void setWidth(Long width) {
		this.width = width;
	}
}
