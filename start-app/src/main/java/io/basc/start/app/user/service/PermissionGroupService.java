package io.basc.start.app.user.service;

import io.basc.framework.context.result.DataResult;
import io.basc.start.app.model.ElementUiTree;
import io.basc.start.app.user.model.PermissionGroupInfo;
import io.basc.start.app.user.pojo.PermissionGroup;

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
