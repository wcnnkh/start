package io.basc.start.bytedance.search;

import java.util.List;

import io.basc.start.bytedance.PagingResponseCode;

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
