package io.basc.app.user.service;

import io.basc.app.model.ElementUiTree;
import io.basc.app.user.model.PermissionGroupInfo;
import io.basc.app.user.pojo.PermissionGroup;
import io.basc.framework.context.result.DataResult;

import java.util.List;

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
