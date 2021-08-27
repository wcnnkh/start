package io.basc.platform.integration.bytedance.search;

import io.basc.platform.integration.bytedance.PagingResponseCode;

import java.util.List;

public class VideoSearchResponse extends PagingResponseCode {
	private static final long serialVersionUID = 1L;
	private List<VideoSearchInfo> list;

	public List<VideoSearchInfo> getList() {
		return list;
	}

	public void setList(List<VideoSearchInfo> list) {
		this.list = list;
	}
}
