package io.basc.start.user.security;

import io.basc.framework.beans.annotation.ConfigurationProperties;
import io.basc.framework.beans.annotation.Value;

@ConfigurationProperties(prefix = "security")
public class SecurityProperties {
	public static final String ADMIN_CONTROLLER = "${security.controller:/admin}";

	/**
	 * 固定值，不监听变更，只能通过重启来更新
	 */
	@Value(value = ADMIN_CONTROLLER, listener = false)
	private String controller;

	private String toLoginPath;

	public String getController() {
		return controller;
	}

	public String getToLoginPath() {
		return toLoginPath == null ? (controller + "/to_login") : toLoginPath;
	}

	public void setToLoginPath(String toLoginPath) {
		this.toLoginPath = toLoginPath;
	}
}
