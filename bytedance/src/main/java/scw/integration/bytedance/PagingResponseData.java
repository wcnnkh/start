package scw.integration.bytedance;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class PagingResponseData<D> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "用于下一页请求的cursor")
	private Long cursor;
	@Schema(description = "后续是否还有数据")
	private Boolean has_more;
	@Schema(description = "总数")
	private Long total;
	@Schema(description = "列表")
	private List<D> list;

	public Long getCursor() {
		return cursor;
	}

	public void setCursor(Long cursor) {
		this.cursor = cursor;
	}

	public Boolean getHas_more() {
		return has_more;
	}

	public void setHas_more(Boolean has_more) {
		this.has_more = has_more;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<D> getList() {
		return list;
	}

	public void setList(List<D> list) {
		this.list = list;
	}
}
