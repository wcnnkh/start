package io.basc.platform.integration.bytedance.comment;

import io.basc.platform.integration.bytedance.PagingResponseCode;

import java.util.List;

public class CommentListResponse extends PagingResponseCode {
	private static final long serialVersionUID = 1L;
	private List<Comment> list;

	public List<Comment> getList() {
		return list;
	}

	public void setList(List<Comment> list) {
		this.list = list;
	}
}
