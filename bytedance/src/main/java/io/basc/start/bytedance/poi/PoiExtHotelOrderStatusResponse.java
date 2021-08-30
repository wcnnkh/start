package io.basc.start.bytedance.poi;

import io.basc.start.bytedance.Response;
import io.basc.start.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class PoiExtHotelOrderStatusResponse extends Response<ResponseCode> {
	private static final long serialVersionUID = 1L;
	@Schema(description = "订单确认状态。0 - 订单确认, 1 - 价格变动, 2 - 库存不足, 3 - 确认中")
	private Long status;

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
}
