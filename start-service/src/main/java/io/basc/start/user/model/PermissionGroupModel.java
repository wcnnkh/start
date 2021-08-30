package io.basc.start.user.model;

import io.basc.framework.mapper.MapperUtils;

import java.io.Serializable;

public class PermissionGroupModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int parentId;
	private String name;
	private boolean disable;//是否禁用

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}
	
	@Override
	public String toString() {
		return MapperUtils.toString(this);
	}
}
