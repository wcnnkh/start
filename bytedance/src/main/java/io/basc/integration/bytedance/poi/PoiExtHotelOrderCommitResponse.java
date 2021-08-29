package io.basc.integration.bytedance.poi;

import io.basc.integration.bytedance.Response;
import io.basc.integration.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class PoiExtHotelOrderCommitResponse extends Response<ResponseCode> {
	private static final long serialVersionUID = 1L;
	@Schema(description = "外部订单id")
	private String order_ext_id;

	public String getOrder_ext_id() {
		return order_ext_id;
	}

	public void setOrder_ext_id(String order_ext_id) {
		this.order_ext_id = order_ext_id;
	}
}
