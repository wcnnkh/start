package io.basc.start.bytedance.data;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalMetadata implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "日期", example = "yyyy-MM-dd")
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
