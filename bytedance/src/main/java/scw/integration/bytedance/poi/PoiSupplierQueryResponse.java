package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.ResponseCode;

public class PoiSupplierQueryResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "数据同步结果")
	private SyncStatus sync_status;
	private PoiSupplier data_detail;

	public SyncStatus getSync_status() {
		return sync_status;
	}

	public void setSync_status(SyncStatus sync_status) {
		this.sync_status = sync_status;
	}

	public PoiSupplier getData_detail() {
		return data_detail;
	}

	public void setData_detail(PoiSupplier data_detail) {
		this.data_detail = data_detail;
	}
}
