package io.basc.start.user.service.impl;

import io.basc.framework.beans.annotation.Autowired;
import io.basc.framework.beans.annotation.Service;
import io.basc.framework.context.result.DataResult;
import io.basc.framework.context.result.Result;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.db.DB;
import io.basc.framework.io.SerializerUtils;
import io.basc.framework.sql.SimpleSql;
import io.basc.framework.util.CollectionUtils;
import io.basc.start.model.ElementUiTree;
import io.basc.start.user.model.PermissionGroupInfo;
import io.basc.start.user.pojo.PermissionGroup;
import io.basc.start.user.service.PermissionGroupActionService;
import io.basc.start.user.service.PermissionGroupService;
import io.basc.start.user.service.config.BaseServiceConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PermissionGroupServiceImpl extends BaseServiceConfiguration implements PermissionGroupService {
	@Autowired
	private PermissionGroupActionService adminRoleGroupActionService;

	public PermissionGroupServiceImpl(DB db, ResultFactory resultFactory) {
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
		return result.dataResult();
	}

	public List<PermissionGroup> getSubList(int id, boolean ergodic) {
		List<PermissionGroup> subList = db.query(PermissionGroup.class,
				new SimpleSql("select * from permission_group where parentId=?", id)).shared();
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
