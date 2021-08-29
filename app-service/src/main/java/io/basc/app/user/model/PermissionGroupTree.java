package io.basc.app.user.model;

import io.basc.app.user.pojo.PermissionGroup;
import io.basc.framework.mapper.MapperUtils;

import java.io.Serializable;
import java.util.List;

public class PermissionGroupTree implements Serializable {
	private static final long serialVersionUID = 1L;
	private final PermissionGroup permissionGroup;
	private final List<PermissionGroupTree> subList;

	public PermissionGroupTree(PermissionGroup permissionGroup,
			List<PermissionGroupTree> subList) {
		this.permissionGroup = permissionGroup;
		this.subList = subList;
	}

	public PermissionGroup getAdminRoleGroup() {
		return permissionGroup;
	}

	public List<PermissionGroupTree> getSubList() {
		return subList;
	}
	
	@Override
	public String toString() {
		return MapperUtils.toString(this);
	}
}
