package io.basc.start.data;

import java.util.List;

import io.basc.framework.lang.Nullable;
import io.basc.framework.util.Pair;
import io.basc.framework.util.page.Paginations;

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
	
	T getByPrimaryKeys(T query);
	
	List<Pair<String, String>> queryOptions(@Nullable T query);
	
	Paginations<T> list(T query, int page, int limit);
}
