package io.basc.start.tencent.wx.open;

public enum Scope {
	BASE("snsapi_base"), LOGIN("snsapi_login"), USERINFO("snsapi_userinfo"),;

	private final String value;

	Scope(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
