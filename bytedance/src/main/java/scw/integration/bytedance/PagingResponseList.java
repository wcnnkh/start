package scw.integration.bytedance;

import java.util.List;

public class PagingResponseList<T> extends PagingResponseCode {
	private static final long serialVersionUID = 1L;
	private Integer total;
	private List<T> list;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}
}
