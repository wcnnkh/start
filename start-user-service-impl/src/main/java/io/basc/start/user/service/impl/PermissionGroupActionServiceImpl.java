package io.basc.start.user.service.impl;

import io.basc.framework.beans.annotation.Service;
import io.basc.framework.context.result.Result;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.db.DB;
import io.basc.start.user.pojo.PermissionGroupAction;
import io.basc.start.user.service.PermissionGroupActionService;
import io.basc.start.user.service.config.BaseServiceConfiguration;

import java.util.Collection;
import java.util.List;

@Service
public class PermissionGroupActionServiceImpl extends BaseServiceConfiguration implements PermissionGroupActionService {

	public PermissionGroupActionServiceImpl(DB db, ResultFactory resultFactory) {
		super(db, resultFactory);
		db.createTable(PermissionGroupAction.class, false);
	}

	public boolean check(int groupId, String actionId) {
		return db.getById(PermissionGroupAction.class, groupId, actionId) != null;
	}

	public Result setActions(int groupId, Collection<String> actionIds) {
		List<PermissionGroupAction> actions = db.getByIdList(PermissionGroupAction.class, groupId);
		if (actions != null) {
			for (PermissionGroupAction action : actions) {
				db.delete(action);
			}
		}

		for (String actionId : actionIds) {
			PermissionGroupAction action = new PermissionGroupAction();
			action.setGroupId(groupId);
			action.setActionId(actionId);
			db.save(action);
		}
		return resultFactory.success();
	}

	public List<PermissionGroupAction> getActionList(int groupId) {
		return db.getByIdList(PermissionGroupAction.class, groupId);
	}

}
