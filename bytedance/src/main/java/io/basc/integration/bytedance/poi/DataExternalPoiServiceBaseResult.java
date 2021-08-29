package io.basc.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class DataExternalPoiServiceBaseResult implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "点击uv", example = "200")
	private Long click_uv;
	@Schema(description = "日期", example = "yyyy-MM-dd")
	private String date;
	@Schema(description = "曝光uv", example = "200")
	private Long exposure_uv;
	@Schema(description = "订房成交次数", example = "200")
	private Long success_order_cnt;

	public Long getClick_uv() {
		return click_uv;
	}

	public void setClick_uv(Long click_uv) {
		this.click_uv = click_uv;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getExposure_uv() {
		return exposure_uv;
	}

	public void setExposure_uv(Long exposure_uv) {
		this.exposure_uv = exposure_uv;
	}

	public Long getSuccess_order_cnt() {
		return success_order_cnt;
	}

	public void setSuccess_order_cnt(Long success_order_cnt) {
		this.success_order_cnt = success_order_cnt;
	}
}
