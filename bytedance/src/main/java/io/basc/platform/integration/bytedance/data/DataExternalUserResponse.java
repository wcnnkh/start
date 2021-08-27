package io.basc.platform.integration.bytedance.data;

import io.basc.platform.integration.bytedance.ResponseCode;

import java.util.List;

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
