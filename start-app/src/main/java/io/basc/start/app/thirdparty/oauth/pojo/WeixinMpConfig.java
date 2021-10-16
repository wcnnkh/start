package io.basc.start.app.thirdparty.oauth.pojo;

import java.io.Serializable;

import io.basc.framework.orm.annotation.PrimaryKey;

public class WeixinMpConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private String appid;
	private String appsecret;
	private String redirectUri;

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

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
}
