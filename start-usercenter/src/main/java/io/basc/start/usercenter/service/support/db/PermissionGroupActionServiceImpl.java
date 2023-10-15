package io.basc.start.usercenter.service.support.db;

import java.util.Collection;
import java.util.List;

import io.basc.framework.context.annotation.Service;
import io.basc.framework.context.transaction.Result;
import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.db.Database;
import io.basc.start.template.service.impl.TemplateServiceSupport;
import io.basc.start.usercenter.pojo.PermissionGroupAction;
import io.basc.start.usercenter.service.PermissionGroupActionService;

@Service
public class PermissionGroupActionServiceImpl extends TemplateServiceSupport implements PermissionGroupActionService {

	public PermissionGroupActionServiceImpl(Database db, ResultFactory resultFactory) {
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
