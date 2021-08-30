package io.basc.satrt.admin.web.editable;

import io.basc.framework.boot.ApplicationPostProcessor;
import io.basc.framework.boot.ConfigurableApplication;
import io.basc.framework.context.annotation.Provider;
import io.basc.framework.logger.Logger;
import io.basc.framework.logger.LoggerFactory;

import java.util.List;

@Provider
public class AuthorityInitializer implements ApplicationPostProcessor {
	private static Logger logger = LoggerFactory
			.getLogger(AuthorityInitializer.class);

	@Override
	public void postProcessApplication(ConfigurableApplication application)
			throws Throwable {
		if (!(canInit(application, EditorRegistry.class) || canInit(
				application, EditorResolver.class))) {
			return;
		}

		EditorRegistry editorRegistry = application.getBeanFactory()
				.getInstance(EditorRegistry.class);
		EditorResolver editorResolver = application.getBeanFactory()
				.getInstance(EditorResolver.class);
		for (Class<?> clazz : application.getContextClasses()) {
			if (!editorResolver.canResolve(clazz)) {
				continue;
			}

			List<Editor> editors = editorResolver.resolve(clazz);
			if (editors == null) {
				continue;
			}

			for (Editor editor : editors) {
				editorRegistry.register(editor);
			}
		}
	}

	private boolean canInit(ConfigurableApplication application, Class<?> clazz) {
		if (!application.getBeanFactory().isInstance(clazz)) {
			logger.error("not instance {}", clazz);
			return false;
		}

		if (!application.getBeanFactory().isSingleton(clazz)) {
			logger.error("{} not is singleton", clazz);
			return false;
		}
		return true;
	}
}
