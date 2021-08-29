package io.basc.integration.bytedance.poi;

import io.basc.integration.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class PoiOrderSyncResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "抖音平台订单ID")
	private String order_id;

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
}
