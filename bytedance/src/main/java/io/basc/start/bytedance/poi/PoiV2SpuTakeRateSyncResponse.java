package io.basc.start.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class PoiV2SpuTakeRateSyncResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "抖音spu id", example = "683048927827388")
	private String spu_id;

	public String getSpu_id() {
		return spu_id;
	}

	public void setSpu_id(String spu_id) {
		this.spu_id = spu_id;
	}
}
