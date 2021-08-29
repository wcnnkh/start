package io.basc.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternBillboardTopic extends Rank {
	private static final long serialVersionUID = 1L;
	@Schema(description = "排名变化", example = "1")
	private String rank_change;
	@Schema(description = "话题标题", example = "测试标题	测试标题")
	private String title;
	@Schema(description = "影响力指数", example = "10000")
	private Double effect_value;

	public String getRank_change() {
		return rank_change;
	}

	public void setRank_change(String rank_change) {
		this.rank_change = rank_change;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getEffect_value() {
		return effect_value;
	}

	public void setEffect_value(Double effect_value) {
		this.effect_value = effect_value;
	}
}
