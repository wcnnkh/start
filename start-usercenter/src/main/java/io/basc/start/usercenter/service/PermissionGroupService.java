package io.basc.start.usercenter.service;

import java.util.List;

import io.basc.framework.context.transaction.DataResult;
import io.basc.start.template.pojo.ElementUiTree;
import io.basc.start.usercenter.model.PermissionGroupInfo;
import io.basc.start.usercenter.pojo.PermissionGroup;

public interface PermissionGroupService {
	PermissionGroup getById(int id);

	List<PermissionGroup> getSubList(int id, boolean ergodic);

	DataResult<PermissionGroupInfo> createOrUpdate(PermissionGroupInfo adminRoleGroupInfo);

	List<ElementUiTree<Integer>> getPermissionGroupTreeList(int parentGroupId);
}
