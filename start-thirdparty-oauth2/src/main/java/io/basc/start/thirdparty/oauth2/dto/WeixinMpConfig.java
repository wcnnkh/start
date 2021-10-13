package io.basc.start.thirdparty.oauth2.dto;

import java.io.Serializable;

import io.basc.framework.orm.annotation.PrimaryKey;

public class WeixinMpConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private String appid;
	private String appsecret;
	private String[] redirectUris;

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

	public String[] getRedirectUris() {
		return redirectUris;
	}

	public void setRedirectUris(String[] redirectUris) {
		this.redirectUris = redirectUris;
	}

}
