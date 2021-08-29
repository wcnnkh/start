package io.basc.integration.bytedance.video;

import java.io.Serializable;

public class VideoDataResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private VideoData list;

	public VideoData getList() {
		return list;
	}

	public void setList(VideoData list) {
		this.list = list;
	}
}
