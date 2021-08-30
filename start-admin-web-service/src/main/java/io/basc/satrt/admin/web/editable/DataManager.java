package io.basc.satrt.admin.web.editable;

import io.basc.framework.context.result.DataResult;
import io.basc.framework.context.result.Result;
import io.basc.framework.lang.Nullable;
import io.basc.framework.util.Pair;
import io.basc.framework.util.page.Page;

import java.util.List;

public interface DataManager {
	<T> Page<T> list(Class<? extends T> type, T query, int page, int limit);

	@Nullable
	<T> T info(Class<? extends T> type, T query);

	<T> Result update(Class<? extends T> type, T result);

	<T> Result delete(Class<? extends T> type, T query);

	<T> DataResult<T> add(Class<? extends T> type, T result);

	List<Pair<String, String>> queryOptions(Class<?> queryClass, @Nullable String query);
}
