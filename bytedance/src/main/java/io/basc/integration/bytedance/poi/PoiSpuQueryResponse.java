package io.basc.integration.bytedance.poi;

import io.basc.integration.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class PoiSpuQueryResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "数据同步结果")
	private SyncStatus sync_status;
	private Spu data_detail;

	public SyncStatus getSync_status() {
		return sync_status;
	}

	public void setSync_status(SyncStatus sync_status) {
		this.sync_status = sync_status;
	}

	public Spu getData_detail() {
		return data_detail;
	}

	public void setData_detail(Spu data_detail) {
		this.data_detail = data_detail;
	}
}
