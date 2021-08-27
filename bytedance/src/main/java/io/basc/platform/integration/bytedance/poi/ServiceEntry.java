package io.basc.platform.integration.bytedance.poi;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class ServiceEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "抖音小程序入口参数")
	private EntryMiniApp entry_mini_app;
	@Schema(description = "入口类型(1:H5，2:抖音小程序)")
	private Long entry_type;

	public EntryMiniApp getEntry_mini_app() {
		return entry_mini_app;
	}

	public void setEntry_mini_app(EntryMiniApp entry_mini_app) {
		this.entry_mini_app = entry_mini_app;
	}

	public Long getEntry_type() {
		return entry_type;
	}

	public void setEntry_type(Long entry_type) {
		this.entry_type = entry_type;
	}
}
