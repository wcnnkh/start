package io.basc.start.app.user.model;

import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PermissionGroupInfo extends PermissionGroupModel {
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
}
