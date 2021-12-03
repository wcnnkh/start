package io.basc.start.data;

import java.util.List;

import io.basc.framework.lang.Nullable;
import io.basc.framework.orm.EntityOperations;
import io.basc.framework.util.Pair;
import io.basc.framework.util.page.Paginations;

public interface DataService extends EntityOperations {
	boolean canOperations(Class<?> entityClass);
	
	<T> Paginations<T> list(Class<? extends T> entityClass, T query, int page, int limit);

	@Nullable
	<T> T getByPrimaryKeys(Class<? extends T> entityClass, T query);

	<T> List<Pair<String, String>> queryOptions(Class<? extends T> entityClass,
			@Nullable T query);
}
