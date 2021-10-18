package io.basc.start.data;

import io.basc.framework.lang.Nullable;
import io.basc.framework.util.Pair;
import io.basc.framework.util.page.Page;

import java.util.List;

public interface DataManager<T> {
	Class<T> getDataType();

	void save(Object entity);

	boolean saveIfAbsent(T entity);

	boolean delete(T entity);

	boolean deleteById(Object... ids);

	boolean update(T entity);

	/**
	 * @see #saveOrUpdate(Class, Object)
	 * @param entity
	 * @return
	 */
	boolean saveOrUpdate(T entity);

	@Nullable
	T getById(Object... ids);
	
	T info(T query);
	
	List<Pair<String, String>> queryOptions(@Nullable T query);
	
	Page<T> list(T query, int page, int limit);
}
