package io.basc.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class PoiExtPresaleGrouponOrderCommitRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "接入方订单id", required = true)
	private String order_ext_id;

	public String getOrder_ext_id() {
		return order_ext_id;
	}

	public void setOrder_ext_id(String order_ext_id) {
		this.order_ext_id = order_ext_id;
	}
}
