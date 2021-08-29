package io.basc.integration.bytedance.user;

import io.basc.integration.bytedance.PagingResponse;

import java.util.List;

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
