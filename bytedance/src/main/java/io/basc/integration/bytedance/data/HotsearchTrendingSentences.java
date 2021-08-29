package io.basc.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class HotsearchTrendingSentences extends HotsearchSentences {
	private static final long serialVersionUID = 1L;
	@Schema(description = "标签: * `0` - 无 * `1` - 新 * `2` - 推荐 * `3` - 热 * `4` - 爆 * `5` - 首发")
	private Long label;

	public Long getLabel() {
		return label;
	}

	public void setLabel(Long label) {
		this.label = label;
	}
}
