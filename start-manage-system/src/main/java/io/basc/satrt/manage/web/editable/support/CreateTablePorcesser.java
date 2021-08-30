package io.basc.satrt.manage.web.editable.support;

import io.basc.framework.boot.ApplicationPostProcessor;
import io.basc.framework.boot.ConfigurableApplication;
import io.basc.framework.context.annotation.Provider;
import io.basc.framework.db.DB;
import io.basc.satrt.manage.web.editable.EditorResolver;
import io.basc.satrt.manage.web.editable.annotation.Editable;

@Provider
public class CreateTablePorcesser implements ApplicationPostProcessor{

	@Override
	public void postProcessApplication(ConfigurableApplication application)
			throws Throwable {
		if(application.isInstance(DB.class) && application.isInstance(EditorResolver.class)){
			DB db = application.getInstance(DB.class);
			for(Class<?> clazz : application.getContextClasses()){
				if(clazz.isAnnotationPresent(Editable.class)){
					db.createTable(clazz);
				}
			}
		}
	}

}
