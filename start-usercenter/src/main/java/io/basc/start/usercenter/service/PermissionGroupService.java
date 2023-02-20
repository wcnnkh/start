package io.basc.start.usercenter.service;

import java.util.List;

import io.basc.framework.context.transaction.DataResult;
import io.basc.start.template.pojo.ElementUiTree;
import io.basc.start.usercenter.model.PermissionGroupInfo;
import io.basc.start.usercenter.pojo.PermissionGroup;

public interface PermissionGroupService {
	PermissionGroup getById(int id);

	/**
	 * 获取子权限组
	 * @param id
	 * @param ergodic 是否递归子集
	 * @return
	 */
	List<PermissionGroup> getSubList(int id, boolean ergodic);

	DataResult<PermissionGroupInfo> createOrUpdate(
			PermissionGroupInfo adminRoleGroupInfo);

	List<ElementUiTree<Integer>> getPermissionGroupTreeList(int parentGroupId);
}
