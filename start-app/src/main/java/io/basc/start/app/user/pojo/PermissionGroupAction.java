package io.basc.start.app.user.pojo;

import io.basc.framework.mapper.MapperUtils;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.framework.sql.orm.annotation.Table;

import java.io.Serializable;

@Table
public class PermissionGroupAction implements Serializable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private int groupId;
	@PrimaryKey
	private String actionId;

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getActionId() {
		return actionId;
	}

	public void setActionId(String actionId) {
		this.actionId = actionId;
	}
	
	@Override
	public String toString() {
		return MapperUtils.toString(this);
	}
}
