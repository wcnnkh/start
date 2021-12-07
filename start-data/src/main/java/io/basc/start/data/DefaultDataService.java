package io.basc.start.data;

import java.util.Collections;
import java.util.List;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.core.Ordered;
import io.basc.framework.factory.ConfigurableServices;
import io.basc.framework.lang.Nullable;
import io.basc.framework.util.Pair;
import io.basc.framework.util.page.PageSupport;
import io.basc.framework.util.page.Paginations;

@Provider(order = Ordered.LOWEST_PRECEDENCE)
@SuppressWarnings("rawtypes")
public class DefaultDataService extends ConfigurableServices<DataManager> implements DataService {

	public DefaultDataService() {
		super(DataManager.class);
	}

	@SuppressWarnings("unchecked")
	@Nullable
	public <T> DataManager<T> getDataManager(Class<? extends T> entityClass) {
		for (DataManager manager : this) {
			if (manager.getDataType().equals(entityClass)) {
				return manager;
			}
		}
		return null;
	}

	@Override
	public boolean canOperations(Class<?> entityClass) {
		return getDataManager(entityClass) != null;
	}

	@Override
	public <T> void save(Class<? extends T> entityClass, T entity) {
		DataManager<T> manager = getDataManager(entityClass);
		if (manager == null) {
			return;
		}
		manager.save(entity);
	}

	@Override
	public <T> boolean saveIfAbsent(Class<? extends T> entityClass, T entity) {
		DataManager<T> manager = getDataManager(entityClass);
		if (manager == null) {
			return false;
		}
		return manager.saveIfAbsent(entity);
	}

	@Override
	public <T> boolean delete(Class<? extends T> entityClass, T entity) {
		DataManager<T> manager = getDataManager(entityClass);
		if (manager == null) {
			return false;
		}
		return manager.delete(entity);
	}

	@Override
	public boolean deleteById(Class<?> entityClass, Object... ids) {
		DataManager<?> manager = getDataManager(entityClass);
		if (manager == null) {
			return false;
		}
		return manager.deleteById(ids);
	}

	@Override
	public <T> boolean update(Class<? extends T> entityClass, T entity) {
		DataManager<T> manager = getDataManager(entityClass);
		if (manager == null) {
			return false;
		}
		return manager.update(entity);
	}

	@Override
	public <T> T getById(Class<? extends T> entityClass, Object... ids) {
		DataManager<T> manager = getDataManager(entityClass);
		if (manager == null) {
			return null;
		}
		return manager.getById(ids);
	}

	@Override
	public <T> T getByPrimaryKeys(Class<? extends T> entityClass, T query) {
		DataManager<T> manager = getDataManager(entityClass);
		if (manager == null) {
			return null;
		}
		return manager.getByPrimaryKeys(query);
	}

	@Override
	public <T> List<Pair<String, String>> queryOptions(Class<? extends T> entityClass, T query) {
		DataManager<T> manager = getDataManager(entityClass);
		if (manager == null) {
			return Collections.emptyList();
		}
		return manager.queryOptions(query);
	}

	@Override
	public <T> Paginations<T> list(Class<? extends T> entityClass, T query, int page, int limit) {
		DataManager<T> manager = getDataManager(entityClass);
		if (manager == null) {
			return PageSupport.emptyPaginations(PageSupport.getStart(page, limit), limit);
		}
		return manager.list(query, page, limit);
	}

}
