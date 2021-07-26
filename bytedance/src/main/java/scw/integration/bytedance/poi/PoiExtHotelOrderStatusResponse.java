package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.ResponseCode;
import scw.integration.bytedance.Response;

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
