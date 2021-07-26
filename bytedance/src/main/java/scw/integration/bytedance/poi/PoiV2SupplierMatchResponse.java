package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.ResponseCode;

public class PoiV2SupplierMatchResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "上传状态(1:成功，2:失败)")
	private Long is_success;
	@Schema(description = "抖音平台任务ID")
	private String task_id;

	public Long getIs_success() {
		return is_success;
	}

	public void setIs_success(Long is_success) {
		this.is_success = is_success;
	}

	public String getTask_id() {
		return task_id;
	}

	public void setTask_id(String task_id) {
		this.task_id = task_id;
	}
}
