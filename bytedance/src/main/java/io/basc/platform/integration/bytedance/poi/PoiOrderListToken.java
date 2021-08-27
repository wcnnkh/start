package io.basc.platform.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class PoiOrderListToken implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "下载订单token,5分钟内有效")
	private String order_file_token;

	public String getOrder_file_token() {
		return order_file_token;
	}

	public void setOrder_file_token(String order_file_token) {
		this.order_file_token = order_file_token;
	}

}
