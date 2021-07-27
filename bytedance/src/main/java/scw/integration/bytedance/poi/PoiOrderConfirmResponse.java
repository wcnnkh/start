package scw.integration.bytedance.poi;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.Response;
import scw.integration.bytedance.ResponseCode;

public class PoiOrderConfirmResponse extends Response<ResponseCode> {
	private static final long serialVersionUID = 1L;
	@Schema(description = "接入方券码ID")
	private List<String> code_list;
	@Schema(description = "接入方订单ID")
	private String order_ext_id;

	public List<String> getCode_list() {
		return code_list;
	}

	public void setCode_list(List<String> code_list) {
		this.code_list = code_list;
	}

	public String getOrder_ext_id() {
		return order_ext_id;
	}

	public void setOrder_ext_id(String order_ext_id) {
		this.order_ext_id = order_ext_id;
	}
}
