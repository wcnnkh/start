package scw.integration.bytedance.poi;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class SyncStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "同步失败原因，抖音风控政策问题，该字段无法提供太多信息，目前审核不通过联系抖音运营做进一步处理")
	private String fail_reason;
	@Schema(description = "最近一次同步状态, 0 - 未处理; 1 - 通过; 2 - 未通过")
	private Long last_sync_status;

	public String getFail_reason() {
		return fail_reason;
	}

	public void setFail_reason(String fail_reason) {
		this.fail_reason = fail_reason;
	}

	public Long getLast_sync_status() {
		return last_sync_status;
	}

	public void setLast_sync_status(Long last_sync_status) {
		this.last_sync_status = last_sync_status;
	}
}
