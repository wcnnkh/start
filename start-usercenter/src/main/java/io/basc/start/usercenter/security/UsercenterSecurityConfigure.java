package io.basc.start.usercenter.security;

import io.basc.framework.context.ioc.annotation.Value;
import io.basc.framework.orm.annotation.ConfigurationProperties;

@ConfigurationProperties(prefix = UsercenterSecurityConfigure.CONFIGURE_PREFIX)
public class UsercenterSecurityConfigure {
	public static final String CONFIGURE_PREFIX = "usercenter.security";
	public static final String CONTROLLER = "${" + CONFIGURE_PREFIX + ".controller:/admin}";

	/**
	 * 固定值，不监听变更，只能通过重启来更新
	 */
	@Value(value = CONTROLLER, listener = false)
	private String controller;

	private String toToAdminLoginPath;

	public String getController() {
		return controller;
	}

	public String getToAdminLoginPath() {
		return toToAdminLoginPath == null ? (controller + "/to_login") : toToAdminLoginPath;
	}
}
