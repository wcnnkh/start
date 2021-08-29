package io.basc.integration.bytedance.poi;

import javax.validation.constraints.NotNull;

import io.basc.integration.bytedance.oauth.ClientRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class PoiV2SupplierQueryTaskRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "第三方上传任务id列表，多个任务id用 , 分割，单次查询最多10个任务。", example = "123,1234", required = true)
	@NotNull
	private String supplier_task_ids;

	public String getSupplier_task_ids() {
		return supplier_task_ids;
	}

	public void setSupplier_task_ids(String supplier_task_ids) {
		this.supplier_task_ids = supplier_task_ids;
	}
}
