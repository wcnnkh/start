package io.basc.start.bytedance.poi;

import io.basc.start.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class PoiSupplierSyncResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "抖音平台商户ID")
	private String supplier_id;

	public String getSupplier_id() {
		return supplier_id;
	}

	public void setSupplier_id(String supplier_id) {
		this.supplier_id = supplier_id;
	}
}
