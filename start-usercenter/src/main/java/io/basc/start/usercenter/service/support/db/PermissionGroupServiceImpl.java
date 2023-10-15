package io.basc.start.usercenter.service.support.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.basc.framework.context.annotation.Autowired;
import io.basc.framework.context.annotation.Service;
import io.basc.framework.context.transaction.DataResult;
import io.basc.framework.context.transaction.Result;
import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.db.Database;
import io.basc.framework.io.SerializerUtils;
import io.basc.framework.jdbc.SimpleSql;
import io.basc.framework.util.CollectionUtils;
import io.basc.start.template.pojo.ElementUiTree;
import io.basc.start.template.service.impl.TemplateServiceSupport;
import io.basc.start.usercenter.model.PermissionGroupInfo;
import io.basc.start.usercenter.pojo.PermissionGroup;
import io.basc.start.usercenter.service.PermissionGroupActionService;
import io.basc.start.usercenter.service.PermissionGroupService;

@Service
public class PermissionGroupServiceImpl extends TemplateServiceSupport implements PermissionGroupService {
	@Autowired
	private PermissionGroupActionService adminRoleGroupActionService;

	public PermissionGroupServiceImpl(Database db, ResultFactory resultFactory) {
		super(db, resultFactory);
		db.createTable(PermissionGroup.class, false);
	}

	public PermissionGroup getById(int id) {
		return db.getById(PermissionGroup.class, id);
	}

	public DataResult<PermissionGroupInfo> createOrUpdate(PermissionGroupInfo adminRoleGroupInfo) {
		PermissionGroup adminRoleGroup = getById(adminRoleGroupInfo.getId());
		if (adminRoleGroup == null) {
			adminRoleGroup = new PermissionGroup();
			adminRoleGroup.setId(adminRoleGroupInfo.getId());
		}
		adminRoleGroup.setName(adminRoleGroupInfo.getName());
		adminRoleGroup.setDisable(adminRoleGroupInfo.isDisable());
		adminRoleGroup.setParentId(adminRoleGroupInfo.getParentId());
		db.saveOrUpdate(adminRoleGroup);
		Result result = adminRoleGroupActionService.setActions(adminRoleGroup.getId(),
				adminRoleGroupInfo.getAuthorityIds());
		if (result.isSuccess()) {
			PermissionGroupInfo info = SerializerUtils.clone(adminRoleGroupInfo);
			info.setId(adminRoleGroup.getId());
			return resultFactory.success(info);
		}
		return result.toReturn();
	}

	public List<PermissionGroup> getSubList(int id, boolean ergodic) {
		List<PermissionGroup> subList = db
				.query(PermissionGroup.class, new SimpleSql("select * from permission_group where parentId=?", id))
				.toList();
		if (!ergodic) {
			return subList;
		}

		if (CollectionUtils.isEmpty(subList)) {
			return Collections.emptyList();
		}

		List<PermissionGroup> list = new ArrayList<PermissionGroup>();
		for (PermissionGroup permissionGroup : subList) {
			list.add(permissionGroup);
			list.addAll(getSubList(permissionGroup.getId(), ergodic));
		}
		return list;
	}

	public List<ElementUiTree<Integer>> getPermissionGroupTreeList(int parentGroupId) {
		List<PermissionGroup> adminRoleGroups = getSubList(parentGroupId, false);
		if (CollectionUtils.isEmpty(adminRoleGroups)) {
			return null;
		}

		List<ElementUiTree<Integer>> elementUiTrees = new ArrayList<ElementUiTree<Integer>>();
		for (PermissionGroup group : adminRoleGroups) {
			ElementUiTree<Integer> tree = new ElementUiTree<Integer>(group.getId(), group.getName(),
					getPermissionGroupTreeList(group.getId()));
			elementUiTrees.add(tree);
		}
		return elementUiTrees;
	}
}
