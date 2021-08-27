package io.basc.platform.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class DataExternalPoiBaseResult implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "详情页pv", example = "200")
	private Long detail_vv;
	@Schema(description = "日期", example = "yyyy-MM-dd")
	private String date;

	public Long getDetail_vv() {
		return detail_vv;
	}

	public void setDetail_vv(Long detail_vv) {
		this.detail_vv = detail_vv;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
