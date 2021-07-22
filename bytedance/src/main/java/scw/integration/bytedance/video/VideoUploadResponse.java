package scw.integration.bytedance.video;

import scw.integration.bytedance.ResponseCode;

public class VideoUploadResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	private VideoInfo video;

	public VideoInfo getVideo() {
		return video;
	}

	public void setVideo(VideoInfo video) {
		this.video = video;
	}
}
