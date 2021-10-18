package io.basc.satrt.app.admin.pojo;

import io.basc.framework.orm.annotation.Entity;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.framework.orm.sql.annotation.AutoIncrement;
import io.basc.start.data.annotation.Editable;

import java.io.Serializable;

@Editable(name = "微信授权配置")
@Entity
public class WxConnectConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@AutoIncrement
	private Integer id;
	private String scope;
	private String redirectUri;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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
