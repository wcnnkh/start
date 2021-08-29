package io.basc.integration.bytedance.poi;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class EntryMiniApp implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "主要用于联调，1-使用测试版的小程序，0或者不填-使用正式版小程序", example = "1")
	private Long is_test;
	@Schema(description = "服务参数json", example = "{\"productId\":115511}")
	private String params;
	@Schema(description = "服务路径", example = "pages/productDetail/productDetail")
	private String path;
	@Schema(description = "小程序的appid", example = "tt34843ubcs")
	private String app_id;

	public Long getIs_test() {
		return is_test;
	}

	public void setIs_test(Long is_test) {
		this.is_test = is_test;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
}
