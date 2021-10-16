package io.basc.start.app.user.service;

import io.basc.framework.context.result.Result;
import io.basc.start.app.user.pojo.PermissionGroupAction;

import java.util.Collection;
import java.util.List;

public interface PermissionGroupActionService {
	boolean check(int groupId, String actionId);

	Result setActions(int groupId, Collection<String> authorityIds);
	
	List<PermissionGroupAction> getActionList(int groupId);
}
