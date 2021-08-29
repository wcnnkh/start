package io.basc.integration.bytedance;

import io.swagger.v3.oas.annotations.media.Schema;

public class PagingResponseCode extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "用于下一页请求的cursor")
	private Long cursor;
	@Schema(description = "后续是否还有数据")
	private Boolean has_more;

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
}
