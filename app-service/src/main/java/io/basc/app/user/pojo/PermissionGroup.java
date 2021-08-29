package io.basc.app.user.pojo;

import io.basc.app.user.model.PermissionGroupModel;
import io.basc.framework.mapper.MapperUtils;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.framework.orm.sql.annotation.AutoIncrement;
import io.basc.framework.orm.sql.annotation.Table;

@Table
public class PermissionGroup extends PermissionGroupModel {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@AutoIncrement
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return MapperUtils.toString(this);
	}
}
