package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class EntryInfo<M> implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "入口类型(1:H5，2:抖音小程序，3:抖音链接)")
	private Long entry_type;
	@Schema(description = "入口链接")
	private String entry_url;
	@Schema(description = "小程序入口参数")
	private M entry_miniApp;

	public Long getEntry_type() {
		return entry_type;
	}

	public void setEntry_type(Long entry_type) {
		this.entry_type = entry_type;
	}

	public String getEntry_url() {
		return entry_url;
	}

	public void setEntry_url(String entry_url) {
		this.entry_url = entry_url;
	}

	public M getEntry_miniApp() {
		return entry_miniApp;
	}

	public void setEntry_miniApp(M entry_miniApp) {
		this.entry_miniApp = entry_miniApp;
	}
}
