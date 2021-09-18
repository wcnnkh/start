package io.basc.start.user.pojo;

import io.basc.framework.mapper.MapperUtils;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.framework.orm.sql.annotation.AutoIncrement;
import io.basc.framework.orm.sql.annotation.Table;
import io.basc.start.user.model.PermissionGroupModel;

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