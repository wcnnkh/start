package io.basc.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PoiOrderConfirmRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "接入方券码ID", required = true)
	@NotEmpty
	private List<String> code_list;
	@Schema(description = "抖音用户唯一标识", required = true)
	@NotNull
	private String open_id;
	@Schema(description = "接入方订单ID", required = true)
	@NotNull
	private String order_ext_id;

	public List<String> getCode_list() {
		return code_list;
	}

	public void setCode_list(List<String> code_list) {
		this.code_list = code_list;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getOrder_ext_id() {
		return order_ext_id;
	}

	public void setOrder_ext_id(String order_ext_id) {
		this.order_ext_id = order_ext_id;
	}

}
