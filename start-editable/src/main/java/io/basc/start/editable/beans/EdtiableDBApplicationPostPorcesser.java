package io.basc.start.editable.beans;

import io.basc.framework.boot.ApplicationPostProcessor;
import io.basc.framework.boot.ConfigurableApplication;
import io.basc.framework.context.annotation.Provider;
import io.basc.framework.core.Ordered;
import io.basc.framework.db.DB;
import io.basc.start.editable.EditableMapper;
import io.basc.start.editable.EditableResolver;

@Provider(order = Ordered.LOWEST_PRECEDENCE)
public class EdtiableDBApplicationPostPorcesser implements
		ApplicationPostProcessor {

	@Override
	public void postProcessApplication(ConfigurableApplication application)
			throws Throwable {
		if (application.isInstance(DB.class)
				&& application.isInstance(EditableResolver.class)) {
			DB db = application.getInstance(DB.class);
			EditableMapper mapper = application
					.getInstance(EditableMapper.class);
			for (Class<?> clazz : application.getContextClasses()) {
				if (mapper.isEditable(clazz)) {
					db.createTable(clazz, false);
				}
			}
		}
	}

}
