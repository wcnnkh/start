package scw.integration.bytedance.poi;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class PoiV2SupplierQuerySupplier implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "商户ID")
	private String supplier_ext_id;
	@Schema(description = "匹配任务ID")
	private String task_id;
	@Schema(description = "抖音POIID")
	private String poi_id;
	@Schema(description = "匹配状态，0-没有匹配，1-匹配中，2-匹配完成")
	private String status;

	public String getSupplier_ext_id() {
		return supplier_ext_id;
	}

	public void setSupplier_ext_id(String supplier_ext_id) {
		this.supplier_ext_id = supplier_ext_id;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}

	public String getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(String poi_id) {
		this.poi_id = poi_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
