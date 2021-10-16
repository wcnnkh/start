package io.basc.satrt.app.admin.editable;

import java.util.List;

import io.basc.framework.boot.ApplicationPostProcessor;
import io.basc.framework.boot.ConfigurableApplication;
import io.basc.framework.context.annotation.Provider;

@Provider
public class AuthorityInitializer implements ApplicationPostProcessor {

	@Override
	public void postProcessApplication(ConfigurableApplication application)
			throws Throwable {
		if(application.isInstance(EditorRegistry.class) && application.isInstance(EditorResolver.class)) {
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
	}
}
