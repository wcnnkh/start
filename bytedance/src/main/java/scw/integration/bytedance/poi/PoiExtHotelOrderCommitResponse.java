package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.Response;
import scw.integration.bytedance.ResponseCode;

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
