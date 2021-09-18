package io.basc.start.simple.dev;

import io.basc.framework.beans.BeanFactoryPostProcessor;
import io.basc.framework.beans.BeanlifeCycleEvent;
import io.basc.framework.beans.BeanlifeCycleEvent.Step;
import io.basc.framework.beans.BeansException;
import io.basc.framework.beans.ConfigurableBeanFactory;
import io.basc.framework.context.annotation.Provider;
import io.basc.framework.core.Ordered;
import io.basc.framework.event.EventListener;
import io.basc.framework.web.cors.Cors;
import io.basc.framework.web.cors.CorsRegistry;

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
		if(!beanFactory.getEnvironment().getBooleanValue("io.basc.start.simple.cors.disable")){
			if (beanFactory.containsDefinition(CorsRegistry.class.getName())) {
				beanFactory.getLifecycleDispatcher().registerListener(this);
			} else {
				CorsRegistry registry = new CorsRegistry();
				registry.add("/**", Cors.DEFAULT);
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
				((CorsRegistry) source).add("/**", Cors.DEFAULT);
			}
		}
	}

}
