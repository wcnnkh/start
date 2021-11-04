package io.basc.start.data.db;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.db.DB;
import io.basc.start.data.DataManager;
import io.basc.start.data.DefaultDataService;
import io.basc.start.data.annotation.Editable;

@Provider
public class DbDataService extends DefaultDataService{
	private final DB db;

	public DbDataService(DB db) {
		this.db = db;
	}
	
	public boolean isEditable(Class<?> entityClass) {
		return entityClass.isAnnotationPresent(Editable.class);
	}

	@Override
	public <T> DataManager<T> getDataManager(Class<? extends T> entityClass) {
		DataManager<T> manager = super.getDataManager(entityClass);
		if (manager == null && isEditable(entityClass)) {
			manager = new DbDataManager<T>(db, entityClass);
		}
		return manager;
	}

}
