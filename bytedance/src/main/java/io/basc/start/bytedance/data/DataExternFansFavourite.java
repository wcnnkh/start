package io.basc.start.bytedance.data;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternFansFavourite implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "排名", example = "1")
	private Integer rank;
	@Schema(description = "关键词", example = "关键词测试")
	private String keyword;
	@Schema(description = "热度指数", example = "10000")
	private Long hot_value;

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Long getHot_value() {
		return hot_value;
	}

	public void setHot_value(Long hot_value) {
		this.hot_value = hot_value;
	}
}
