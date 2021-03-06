package io.basc.start.bytedance.user;

import java.util.List;

import io.basc.start.bytedance.PagingResponse;

public class FollowingListResponse extends PagingResponse {
	private static final long serialVersionUID = 1L;
	private List<UserinfoResponse> list;

	public List<UserinfoResponse> getList() {
		return list;
	}

	public void setList(List<UserinfoResponse> list) {
		this.list = list;
	}
}
