package io.basc.start.data.db;

import io.basc.framework.boot.ApplicationPostProcessor;
import io.basc.framework.boot.ConfigurableApplication;
import io.basc.framework.context.annotation.Provider;
import io.basc.framework.core.Ordered;
import io.basc.framework.db.DB;
import io.basc.start.data.annotation.Editable;

@Provider(order = Ordered.LOWEST_PRECEDENCE)
public class CreateTablePorcesser implements ApplicationPostProcessor {

	@Override
	public void postProcessApplication(ConfigurableApplication application) throws Throwable {
		if (application.isInstance(DB.class)) {
			DB db = application.getInstance(DB.class);
			for (Class<?> clazz : application.getContextClasses()) {
				if (clazz.isAnnotationPresent(Editable.class)) {
					db.createTable(clazz, false);
				}
			}
		}
	}

}
