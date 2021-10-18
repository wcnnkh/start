package io.basc.satrt.app.admin.pojo;

import io.basc.framework.orm.annotation.Entity;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.start.data.annotation.Editable;

import java.io.Serializable;

@Editable(name = "微信基础配置")
@Entity
public class WeixinMpConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private String appid;
	private String appsecret;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
}
