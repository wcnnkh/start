package io.basc.satrt.app.admin;

import io.basc.framework.boot.ApplicationPostProcessor;
import io.basc.framework.boot.ConfigurableApplication;
import io.basc.framework.context.annotation.ConditionalOnParameters;
import io.basc.framework.core.Ordered;
import io.basc.framework.util.StringUtils;
import io.basc.framework.web.cors.Cors;
import io.basc.framework.web.cors.CorsRegistry;
import io.basc.start.usercenter.security.UsercenterSecurityConfigure;

/**
 * 默认的cors自动配置
 * 
 * @author shuchaowen
 *
 */
@ConditionalOnParameters(order = Ordered.LOWEST_PRECEDENCE)
public class CorsAutoConfigure implements ApplicationPostProcessor {

	@Override
	public void postProcessApplication(ConfigurableApplication application) throws Throwable {
		// 判断是否禁用
		if (!application.getProperties().getAsBoolean("io.basc.start.app.admin.cors.disable")) {
			UsercenterSecurityConfigure appConfigure = application.getInstance(UsercenterSecurityConfigure.class);
			CorsRegistry corsRegistry = application.getInstance(CorsRegistry.class);
			corsRegistry.add(StringUtils.cleanPath(appConfigure.getController() + "/**"), Cors.DEFAULT);
		}
	}
}
