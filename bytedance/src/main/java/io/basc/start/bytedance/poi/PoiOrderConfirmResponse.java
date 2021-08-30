package io.basc.start.bytedance.poi;

import java.util.List;

import io.basc.start.bytedance.Response;
import io.basc.start.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

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
