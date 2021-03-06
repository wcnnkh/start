package io.basc.start.bytedance.poi;

import io.basc.start.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class PoiV2SpuStockUpdateResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "接入方SPU ID")
	private String spu_ext_id;

	public String getSpu_ext_id() {
		return spu_ext_id;
	}

	public void setSpu_ext_id(String spu_ext_id) {
		this.spu_ext_id = spu_ext_id;
	}
}
