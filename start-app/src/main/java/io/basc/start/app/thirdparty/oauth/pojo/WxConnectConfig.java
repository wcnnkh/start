package io.basc.start.app.thirdparty.oauth.pojo;

import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.framework.orm.sql.annotation.AutoIncrement;

import java.io.Serializable;

public class WxConnectConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@AutoIncrement
	private int id;
	private String scope;
	private String redirectUri;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}
}
