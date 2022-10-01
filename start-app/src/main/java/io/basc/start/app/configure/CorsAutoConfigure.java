package io.basc.start.app.configure;

import io.basc.framework.boot.ApplicationPostProcessor;
import io.basc.framework.boot.ConfigurableApplication;
import io.basc.framework.context.annotation.Provider;
import io.basc.framework.core.Ordered;
import io.basc.framework.util.StringUtils;
import io.basc.framework.web.cors.Cors;
import io.basc.framework.web.cors.CorsRegistry;

/**
 * 默认的cors自动配置
 * 
 * @author shuchaowen
 *
 */
@Provider(order = Ordered.LOWEST_PRECEDENCE)
public class CorsAutoConfigure implements ApplicationPostProcessor {

	@Override
	public void postProcessApplication(ConfigurableApplication application) throws Throwable {
		// 判断是否禁用
		if (!application.getProperties().getBooleanValue("io.basc.start.app.admin.cors.disable")) {
			AppConfigure appConfigure = application.getInstance(AppConfigure.class);
			CorsRegistry corsRegistry = application.getInstance(CorsRegistry.class);
			corsRegistry.add(StringUtils.cleanPath(appConfigure.getAdminController() + "/**"), Cors.DEFAULT);
		}
	}
}
