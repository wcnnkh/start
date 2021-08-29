package io.basc.app.user.service;

import io.basc.app.user.enums.AccountType;
import io.basc.app.user.enums.UnionIdType;
import io.basc.app.user.model.AdminUserModel;
import io.basc.app.user.model.UserAttributeModel;
import io.basc.app.user.pojo.UidToUnionId;
import io.basc.app.user.pojo.UnionIdToUid;
import io.basc.app.user.pojo.User;
import io.basc.framework.context.result.DataResult;
import io.basc.framework.context.result.Result;
import io.basc.framework.util.page.Page;

import java.util.Collection;

public interface UserService {
	/**
	 * 搜索permissionGroupId>0的用户
	 * @param permissionGroupIds
	 * @param search
	 * @param page
	 * @param limit
	 * @return
	 */
	Page<User> search(Collection<Integer> permissionGroupIds, String search, int page, int limit);

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
