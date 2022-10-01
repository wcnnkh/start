package io.basc.start.app.configure;

import io.basc.framework.context.ioc.annotation.Value;
import io.basc.framework.orm.annotation.ConfigurationProperties;

@ConfigurationProperties(prefix = AppConfigure.CONFIGURE_PREFIX)
public class AppConfigure {
	public static final String CONFIGURE_PREFIX = "start.app";
	public static final String ADMIN_CONTROLLER = "${" + CONFIGURE_PREFIX
			+ ".admin.controller:/admin}";

	private String host;

	/**
	 * 固定值，不监听变更，只能通过重启来更新
	 */
	@Value(value = ADMIN_CONTROLLER, listener = false)
	private String adminController;

	private String toToAdminLoginPath;

	public String getAdminController() {
		return adminController;
	}

	public String getToAdminLoginPath() {
		return toToAdminLoginPath == null ? (getAdminController() + "/to_login")
				: toToAdminLoginPath;
	}

	public String getHost() {
		return host;
	}
}
