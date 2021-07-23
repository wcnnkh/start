package scw.integration.bytedance;

import java.util.List;

public class PagingResponseCodeList<T> extends PagingResponseCode {
	private static final long serialVersionUID = 1L;
	private List<T> list;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
