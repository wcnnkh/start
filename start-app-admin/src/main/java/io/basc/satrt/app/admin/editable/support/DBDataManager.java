package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.context.result.DataResult;
import io.basc.framework.context.result.Result;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.db.DB;
import io.basc.framework.mapper.Field;
import io.basc.framework.mapper.Fields;
import io.basc.framework.mapper.MapperUtils;
import io.basc.framework.sql.SimpleSql;
import io.basc.framework.sql.Sql;
import io.basc.framework.sql.SqlUtils;
import io.basc.framework.sql.WhereSql;
import io.basc.framework.util.Pair;
import io.basc.framework.util.StringUtils;
import io.basc.framework.util.page.Page;
import io.basc.satrt.app.admin.editable.DataManager;
import io.basc.satrt.app.admin.editable.EditableExcpetion;
import io.basc.satrt.app.admin.editable.annotation.SelectOption;

import java.util.List;
import java.util.stream.Collectors;

@Provider
public class DBDataManager implements DataManager {
	private final DB db;
	private final ResultFactory resultFactory;

	public DBDataManager(DB db, ResultFactory resultFactory) {
		this.db = db;
		this.resultFactory = resultFactory;
	}

	@Override
	public <T> Page<T> list(Class<? extends T> type, T query, int page, int limit) {
		WhereSql where = new WhereSql();
		for (Field field : MapperUtils.getFields(type).entity().all()) {
			Object value = field.getGetter().get(query);
			if(value == null) {
				continue;
			}
			
			if(StringUtils.isEmpty(value)) {
				continue;
			}
			
			where.and(field.getGetter().getName() + "=?", value);
		}
		Sql sql = where.assembleSql("select * from " + StringUtils.humpNamingReplacement(type.getSimpleName(), "_"), null);
		return db.getPage(type, sql, page, limit);
	}

	@Override
	public <T> T info(Class<? extends T> type, T query) {
		WhereSql where = new WhereSql();
		for (Field field : MapperUtils.getFields(type).entity().all()) {
			Object value = field.getGetter().get(query);
			if(value == null) {
				continue;
			}
			
			where.and(field.getGetter().getName() + "=?", value);
		}
		Sql sql = where.assembleSql("select * from " + StringUtils.humpNamingReplacement(type.getSimpleName(), "_"), null);
		return db.query(type, sql).first();
	}

	@Override
	public <T> Result update(Class<? extends T> type, T result) {
		db.update(result);
		return resultFactory.success(result);
	}

	@Override
	public <T> Result delete(Class<? extends T> type, T query) {
		db.delete(query);
		return resultFactory.success();
	}

	@Override
	public <T> DataResult<T> add(Class<? extends T> type, T result) {
		db.save(result);
		return resultFactory.success(result);
	}

	@Override
	public List<Pair<String, String>> queryOptions(Class<?> queryClass, String query) {
		Fields primaryKeys = db.getSqlDialect().getPrimaryKeys(queryClass).shared();
		if (primaryKeys.getCount() != 1) {
			throw new EditableExcpetion("查询选项的主键数量只能存在一个");
		}
		Field queryField = db.getSqlDialect().getFields(queryClass).stream()
				.filter((f) -> f.isAnnotationPresent(SelectOption.class)).findFirst().get();
		String sql = "select * from " + db.getSqlDialect().getName(queryClass);
		Sql querySql = StringUtils.isEmpty(query) ? new SimpleSql(sql)
				: new SimpleSql(sql + " where `" + db.getSqlDialect().getName(queryField.getSetter()) + "` like ?",
						SqlUtils.toLikeValue(query));
		return db.query(queryClass, querySql).map((obj) -> {
			String id = String.valueOf(primaryKeys.first().getGetter().get(obj));
			String text = String.valueOf(queryField.getGetter().get(obj));
			return new Pair<String, String>(id, text);
		}).collect(Collectors.toList());
	}
}
