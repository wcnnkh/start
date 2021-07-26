package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

public class PoiExtPresaleGrouponOrder implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "接入方订单id")
	private String order_ext_id;
	@Schema(description = "预约券券码列表")
	private List<String> code_list;

	public String getOrder_ext_id() {
		return order_ext_id;
	}

	public void setOrder_ext_id(String order_ext_id) {
		this.order_ext_id = order_ext_id;
	}

	public List<String> getCode_list() {
		return code_list;
	}

	public void setCode_list(List<String> code_list) {
		this.code_list = code_list;
	}
}
