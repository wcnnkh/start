package io.basc.start.usercenter.service;

import java.util.Collection;
import java.util.List;

import io.basc.framework.context.transaction.Result;
import io.basc.start.usercenter.pojo.PermissionGroupAction;

public interface PermissionGroupActionService {
	boolean check(int groupId, String actionId);

	Result setActions(int groupId, Collection<String> authorityIds);
	
	List<PermissionGroupAction> getActionList(int groupId);
}
