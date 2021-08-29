package io.basc.integration.bytedance;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class PagingRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "分页游标, 第一页请求cursor是0, response中会返回下一页请求用到的cursor, 同时response还会返回has_more来表明是否有更多的数据。", example = "0")
	private Long cursor;
	@Schema(description = "每页数量", example = "10", required = true)
	@NotNull
	@Min(0)
	private Integer count;

	public Long getCursor() {
		return cursor;
	}

	public void setCursor(Long cursor) {
		this.cursor = cursor;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
