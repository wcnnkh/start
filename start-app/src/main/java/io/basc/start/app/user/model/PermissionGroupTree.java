package io.basc.start.app.user.model;

import java.io.Serializable;
import java.util.List;

import io.basc.start.app.user.pojo.PermissionGroup;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PermissionGroupTree implements Serializable {
	private static final long serialVersionUID = 1L;
	private final PermissionGroup permissionGroup;
	private final List<PermissionGroupTree> subList;
}
