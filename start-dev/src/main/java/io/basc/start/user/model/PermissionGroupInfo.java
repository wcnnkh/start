package io.basc.start.user.model;

import io.basc.framework.mapper.MapperUtils;

import java.util.Collection;

public class PermissionGroupInfo extends PermissionGroupModel{
	private static final long serialVersionUID = 1L;
	private int id;
	private Collection<String> authorityIds;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Collection<String> getAuthorityIds() {
		return authorityIds;
	}
	public void setAuthorityIds(Collection<String> authorityIds) {
		this.authorityIds = authorityIds;
	}
	
	@Override
	public String toString() {
		return MapperUtils.toString(this);
	}
}
