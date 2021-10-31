package io.basc.start.data.db;

import io.basc.framework.db.DB;
import io.basc.framework.sql.Sql;
import io.basc.framework.sql.WhereSql;
import io.basc.framework.sql.orm.Column;
import io.basc.framework.sql.orm.TableStructure;
import io.basc.framework.util.Pair;
import io.basc.framework.util.StringUtils;
import io.basc.framework.util.page.Pages;
import io.basc.start.data.DataException;
import io.basc.start.data.DataManager;
import io.basc.start.data.annotation.SelectOption;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DbDataManager<T> implements DataManager<T> {
	private final Class<? extends T> entityClass;
	private final DB db;

	public DbDataManager(DB db, Class<? extends T> entityClass) {
		this.db = db;
		this.entityClass = entityClass;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Class<T> getDataType() {
		return (Class<T>) entityClass;
	}

	@Override
	public void save(Object entity) {
		db.save(entityClass, entity);
	}

	@Override
	public boolean saveIfAbsent(T entity) {
		return db.saveIfAbsent(entityClass, entity);
	}

	@Override
	public boolean delete(T entity) {
		return db.delete(entityClass, entity);
	}

	@Override
	public boolean deleteById(Object... ids) {
		return db.deleteById(entityClass, ids);
	}

	@Override
	public boolean update(T entity) {
		return db.update(entityClass, entity);
	}

	@Override
	public boolean saveOrUpdate(T entity) {
		return db.saveOrUpdate(entityClass, entity);
	}

	@Override
	public T getById(Object... ids) {
		return db.getById(entityClass, ids);
	}

	@Override
	public T info(T query) {
		TableStructure tableStructure = db.resolve(entityClass, query, null);
		WhereSql where = new WhereSql();
		if (query != null) {
			tableStructure.columns().forEach((column) -> {
				Object value = column.getField().getGetter().get(query);
				if (value == null) {
					return ;
				}

				where.and("`" + column.getName() + "`=?", value);
			});
		}
		Sql sql = where.assembleSql("select * from " + tableStructure.getName(), null);
		return db.query(entityClass, sql).first();
	}

	@Override
	public List<Pair<String, String>> queryOptions(T query) {
		TableStructure tableStructure = db.resolve(entityClass, query, null);
		if (tableStructure.getPrimaryKeys().size() != 1) {
			throw new DataException("主键数量只能存在一个[" + entityClass + "]");
		}

		Optional<Column> queryColumn = tableStructure.columns()
				.filter((c) -> c.getField().isAnnotationPresent(SelectOption.class)).findFirst();
		if (!queryColumn.isPresent()) {
			throw new DataException("无法获取SelectOption[" + entityClass + "]");
		}

		Pages<T> pages = list(query, 0, 100);
		return pages.streamAll().map((obj) -> {
			String id = String.valueOf(tableStructure.getPrimaryKeys().get(0).getField().getGetter().get(obj));
			String text = String.valueOf(queryColumn.get().getField().getGetter().get(obj));
			return new Pair<String, String>(id, text);
		}).collect(Collectors.toList());
	}

	@Override
	public Pages<T> list(T query, int page, int limit) {
		TableStructure tableStructure = db.resolve(entityClass, query, null);
		WhereSql where = new WhereSql();
		tableStructure.columns().forEach((column) -> {
			Object value = column.getField().getGetter().get(query);
			if (value == null) {
				return ;
			}

			if (StringUtils.isEmpty(value)) {
				return ;
			}

			where.and(column.getField().getGetter().getName() + "=?", value);
		});
		Sql sql = where.assembleSql("select * from `" + tableStructure.getName() + "`", null);
		return db.getPages(entityClass, sql, page, limit);
	}
}
