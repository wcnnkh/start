package io.basc.start.bytedance.poi;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class MiniApp implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "小程序的appid")
	private String app_id;
	@Schema(description = "用户的抖音小程序openid")
	private String user_open_id;

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getUser_open_id() {
		return user_open_id;
	}

	public void setUser_open_id(String user_open_id) {
		this.user_open_id = user_open_id;
	}

}
