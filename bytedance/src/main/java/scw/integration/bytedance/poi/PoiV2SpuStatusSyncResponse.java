package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import scw.integration.bytedance.ResponseCode;

public class PoiV2SpuStatusSyncResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "状态同步成功接入方店铺ID列表", example = "[y0001 y0002]")
	private List<String> supplier_ext_id_list;

	public List<String> getSupplier_ext_id_list() {
		return supplier_ext_id_list;
	}

	public void setSupplier_ext_id_list(List<String> supplier_ext_id_list) {
		this.supplier_ext_id_list = supplier_ext_id_list;
	}
}
