package io.basc.start.bytedance.poi;

import io.basc.start.bytedance.oauth.ClientRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class PoiOrderListTokenRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(required = true)
	private String order_date;

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
}
