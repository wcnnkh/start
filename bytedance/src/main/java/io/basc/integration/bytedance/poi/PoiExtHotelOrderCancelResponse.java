package io.basc.integration.bytedance.poi;

import io.basc.integration.bytedance.Response;
import io.basc.integration.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class PoiExtHotelOrderCancelResponse extends Response<ResponseCode> {
	private static final long serialVersionUID = 1L;
	@Schema(description = "取消订单确认状态码；0 - 接受取消")
	private Long status;

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
}
