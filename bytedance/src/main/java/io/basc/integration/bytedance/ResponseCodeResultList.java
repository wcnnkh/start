package io.basc.integration.bytedance;

import java.util.List;

public class ResponseCodeResultList<T> extends ResponseCode {
	private static final long serialVersionUID = 1L;
	private List<T> result_list;

	public List<T> getResult_list() {
		return result_list;
	}

	public void setResult_list(List<T> result_list) {
		this.result_list = result_list;
	}
}
