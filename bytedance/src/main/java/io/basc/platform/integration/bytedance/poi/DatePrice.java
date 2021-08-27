package io.basc.platform.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class DatePrice implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "入住日价格, 人民币分")
	private Long date_price;
	@Schema(description = "入住日期 yyyyMMdd")
	private String date;

	public Long getDate_price() {
		return date_price;
	}

	public void setDate_price(Long date_price) {
		this.date_price = date_price;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
