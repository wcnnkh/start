package io.basc.integration.bytedance.poi;

import javax.validation.constraints.NotNull;

import io.basc.integration.bytedance.oauth.ClientRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class PoiSupplierQueryRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "第三方店铺id列表，多个商铺id用 , 分割，单次查询最多50个店铺。(注意v2接口才支持多个查询)", example = "4123413,1243", required = true)
	@NotNull
	private String supplier_ext_id;

	public String getSupplier_ext_id() {
		return supplier_ext_id;
	}

	public void setSupplier_ext_id(String supplier_ext_id) {
		this.supplier_ext_id = supplier_ext_id;
	}
}
