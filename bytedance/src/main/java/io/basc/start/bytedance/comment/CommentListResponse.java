package io.basc.start.bytedance.comment;

import java.util.List;

import io.basc.start.bytedance.PagingResponseCode;

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
