package io.basc.platform.integration.bytedance.video;

import java.util.List;

import io.basc.platform.integration.bytedance.PagingResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class VideoListResponse extends PagingResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "由于置顶的原因, list长度可能比count指定的数量多一些或少一些。")
	private List<VideoData> list;

	public List<VideoData> getList() {
		return list;
	}

	public void setList(List<VideoData> list) {
		this.list = list;
	}
}
