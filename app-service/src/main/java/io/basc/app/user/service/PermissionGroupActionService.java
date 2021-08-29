package io.basc.app.user.service;

import io.basc.app.user.pojo.PermissionGroupAction;
import io.basc.framework.context.result.Result;

import java.util.Collection;
import java.util.List;

public interface PermissionGroupActionService {
	boolean check(int groupId, String actionId);

	Result setActions(int groupId, Collection<String> authorityIds);
	
	List<PermissionGroupAction> getActionList(int groupId);
}
