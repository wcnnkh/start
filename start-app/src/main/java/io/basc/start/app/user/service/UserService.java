package io.basc.start.app.user.service;

import java.util.Collection;
import java.util.List;

import io.basc.framework.context.result.DataResult;
import io.basc.framework.context.result.Result;
import io.basc.framework.util.page.Pagination;
import io.basc.start.app.user.enums.AccountType;
import io.basc.start.app.user.enums.UnionIdType;
import io.basc.start.app.user.model.AdminUserModel;
import io.basc.start.app.user.model.UserAttributeModel;
import io.basc.start.app.user.pojo.UidToUnionId;
import io.basc.start.app.user.pojo.UnionIdToUid;
import io.basc.start.app.user.pojo.User;

public interface UserService {
	/**
	 * 搜索permissionGroupId>0的用户
	 * @param permissionGroupIds
	 * @param search
	 * @param page
	 * @param limit
	 * @return
	 */
	Pagination<User> search(Collection<Integer> permissionGroupIds, String search, int page, int limit);
	
	List<User> getUsers(Collection<Long> uids);

	User getUser(long uid);

	/**
	 * 是否是超级管理员
	 * 
	 * @param uid
	 * @return
	 */
	boolean isSuperAdmin(long uid);
	
	User getUserByAccount(AccountType type, String account);
	
	User getUserByAccount(AccountType type, String account, String password);
	
	User getUserByUnionId(String unionId, UnionIdType type);
	
	User getUserByUnionId(String unionId, int type);
	
	UidToUnionId getUnionIdByUid(long uid, UnionIdType type);
	
	UidToUnionId getUnionIdByUid(long uid, int type);
	
	UnionIdToUid getUidByUnionId(String unionId, UnionIdType type);
	
	UnionIdToUid getUidByUnionId(String unionId, int type);
	
	Result checkPassword(long uid, String password);

	Result updatePassword(long uid, String password);

	DataResult<User> createOrUpdateAdminUser(long uid, AdminUserModel adminUserModel);
	
	DataResult<User> register(AccountType accountType, String account, String password, UserAttributeModel userAttributeModel);

	DataResult<User> bind(long uid, AccountType accountType, String account);
	
	DataResult<User> registerUnionId(UnionIdType type, String unionId, String password, UserAttributeModel userAttributeModel);

	DataResult<User> registerUnionId(int unionIdType, String unionId, String password, UserAttributeModel userAttributeModel);
	
	Result bindUnionId(long uid, UnionIdType type, String unionId);
	
	Result bindUnionId(long uid, int unionIdType, String unionId);

	Result updateUserAttribute(long uid, UserAttributeModel userAttributeModel);
	
	Result updateDefaultAddressId(long uid, long addressId);
}
