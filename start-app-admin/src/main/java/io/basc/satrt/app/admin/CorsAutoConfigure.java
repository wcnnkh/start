package io.basc.satrt.app.admin;

import io.basc.framework.beans.BeanFactory;
import io.basc.framework.beans.BeanFactoryPostProcessor;
import io.basc.framework.beans.BeanlifeCycleEvent;
import io.basc.framework.beans.BeanlifeCycleEvent.Step;
import io.basc.framework.beans.BeansException;
import io.basc.framework.beans.ConfigurableBeanFactory;
import io.basc.framework.context.annotation.Provider;
import io.basc.framework.core.Ordered;
import io.basc.framework.event.EventListener;
import io.basc.framework.util.StringUtils;
import io.basc.framework.web.cors.Cors;
import io.basc.framework.web.cors.CorsRegistry;
import io.basc.start.app.configure.AppConfigure;

/**
 * 默认的cors自动配置
 * @author shuchaowen
 *
 */
@Provider(order = Ordered.LOWEST_PRECEDENCE)
public class CorsAutoConfigure implements BeanFactoryPostProcessor,
		EventListener<BeanlifeCycleEvent> {

	@Override
	public void postProcessBeanFactory(ConfigurableBeanFactory beanFactory)
			throws BeansException {
		//判断是否禁用
		if(!beanFactory.getEnvironment().getBooleanValue("io.basc.start.app.admin.cors.disable")){
			if (beanFactory.containsDefinition(CorsRegistry.class.getName())) {
				beanFactory.getLifecycleDispatcher().registerListener(this);
			} else {
				CorsRegistry registry = new CorsRegistry();
				configure(beanFactory, registry);
				beanFactory.registerSingleton(CorsRegistry.class.getName(),
						registry);
			}
		}
	}

	@Override
	public void onEvent(BeanlifeCycleEvent event) {
		if (event.getStep() == Step.AFTER_DEPENDENCE
				&& event.getSource() != null) {
			Object source = event.getSource();
			if (source instanceof CorsRegistry) {
				configure(event.getBeanFactory(), ((CorsRegistry) source));
			}
		}
	}

	protected void configure(BeanFactory beanFactory, CorsRegistry registry) {
		AppConfigure appConfigure = beanFactory.getInstance(AppConfigure.class);
		registry.add(StringUtils.cleanPath(appConfigure.getAdminController() + "/**"), Cors.DEFAULT);
	}
}
