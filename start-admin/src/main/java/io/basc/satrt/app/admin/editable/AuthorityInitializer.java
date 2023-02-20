package io.basc.satrt.app.admin.editable;

import java.util.List;

import io.basc.framework.boot.ApplicationPostProcessor;
import io.basc.framework.boot.ConfigurableApplication;
import io.basc.framework.context.annotation.Provider;

@Provider
public class AuthorityInitializer implements ApplicationPostProcessor {

	@Override
	public void postProcessApplication(ConfigurableApplication application) throws Throwable {
		if (application.isInstance(EditorRegistry.class) && application.isInstance(EditorMapper.class)) {
			EditorRegistry editorRegistry = application.getInstance(EditorRegistry.class);
			EditorMapper editorResolver = application.getInstance(EditorMapper.class);
			for (Class<?> clazz : application.getContextClasses()) {
				if (!editorResolver.isEditable(clazz)) {
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
