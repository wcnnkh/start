package io.basc.start.bytedance.data;

import java.util.List;

import io.basc.start.bytedance.ResponseCode;

public class DataExternalUserResponse<T> extends ResponseCode {
	private static final long serialVersionUID = 1L;
	private List<T> result_list;

	public List<T> getResult_list() {
		return result_list;
	}

	public void setResult_list(List<T> result_list) {
		this.result_list = result_list;
	}
}
