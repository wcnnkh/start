package io.basc.start.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

public class PoiV2SupplierQueryTask extends PoiV2Supplier {
	private static final long serialVersionUID = 1L;
	@Schema(description = "匹配状态描述")
	private String mismatch_status_desc;
	@Schema(description = "匹配状态，0-等待匹配，1-正在匹配，2-匹配成功，3-匹配失败")
	private Long match_status;

	public String getMismatch_status_desc() {
		return mismatch_status_desc;
	}

	public void setMismatch_status_desc(String mismatch_status_desc) {
		this.mismatch_status_desc = mismatch_status_desc;
	}

	public Long getMatch_status() {
		return match_status;
	}

	public void setMatch_status(Long match_status) {
		this.match_status = match_status;
	}
}
