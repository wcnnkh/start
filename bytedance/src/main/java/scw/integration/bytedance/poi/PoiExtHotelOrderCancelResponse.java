package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.Response;
import scw.integration.bytedance.ResponseCode;

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
