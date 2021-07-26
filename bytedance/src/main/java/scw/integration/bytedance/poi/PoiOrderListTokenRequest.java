package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.oauth.ClientRequest;

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
