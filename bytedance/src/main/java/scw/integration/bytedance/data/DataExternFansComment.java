package scw.integration.bytedance.data;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternFansComment implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "评论热词", example = "热词测试")
	private String keyword;
	@Schema(description = "热度指数", example = "10000")
	private Long hot_value;

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
