package io.basc.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternBillboardStars extends RankMetadata {
	private static final long serialVersionUID = 1L;
	@Schema(description = "影响力指数", example = "10000")
	private Double effect_value;

	public Double getEffect_value() {
		return effect_value;
	}

	public void setEffect_value(Double effect_value) {
		this.effect_value = effect_value;
	}
}
