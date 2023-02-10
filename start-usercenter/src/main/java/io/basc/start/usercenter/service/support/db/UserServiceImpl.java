package io.basc.start.usercenter.service.support.db;

import java.util.Collection;
import java.util.List;

import io.basc.framework.codec.Encoder;
import io.basc.framework.codec.support.CharsetCodec;
import io.basc.framework.context.annotation.Service;
import io.basc.framework.context.ioc.annotation.Autowired;
import io.basc.framework.context.transaction.DataResult;
import io.basc.framework.context.transaction.Result;
import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.db.DB;
import io.basc.framework.env.Environment;
import io.basc.framework.env.Sys;
import io.basc.framework.event.EventTypes;
import io.basc.framework.orm.repository.Conditions;
import io.basc.framework.orm.repository.ConditionsBuilder;
import io.basc.framework.sql.SimpleSql;
import io.basc.framework.sql.Sql;
import io.basc.framework.util.CollectionUtils;
import io.basc.framework.util.StringUtils;
import io.basc.framework.util.page.Pagination;
import io.basc.start.template.event.AppEvent;
import io.basc.start.template.event.AppEventDispatcher;
import io.basc.start.template.service.impl.TemplateServiceSupport;
import io.basc.start.usercenter.enums.AccountType;
import io.basc.start.usercenter.enums.UnionIdType;
import io.basc.start.usercenter.model.AdminUserModel;
import io.basc.start.usercenter.model.UserAttributeModel;
import io.basc.start.usercenter.pojo.UidToUnionId;
import io.basc.start.usercenter.pojo.UnionIdToUid;
import io.basc.start.usercenter.pojo.User;
import io.basc.start.usercenter.service.PermissionGroupService;
import io.basc.start.usercenter.service.UserService;

@Service
public class UserServiceImpl extends TemplateServiceSupport implements UserService {
	public static String ADMIN_NAME = Sys.getEnv().getProperties().get("io.basc.app.admin.username").or("admin")
			.getAsString();

	private static final Encoder<String, String> PASSWORD_ENCODER = CharsetCodec.UTF_8.toMD5();

	@Autowired
	private PermissionGroupService permissionGroupService;
	@Autowired
	private AppEventDispatcher appEventDispatcher;

	public UserServiceImpl(DB db, ResultFactory resultFactory, Environment environment) {
		super(db, resultFactory);
		db.createTable(User.class, false);
		User user = getUserByAccount(AccountType.USERNAME, ADMIN_NAME);
		if (user == null) {
			AdminUserModel adminUserModel = new AdminUserModel();
			adminUserModel.setUsername(ADMIN_NAME);
			adminUserModel.setNickname(
					environment.getProperties().get("io.basc.start.app.admin.nickname").or("超级管理员").getAsString());
			adminUserModel.setPassword(
					environment.getProperties().get("io.basc.start.app.admin.password").or("123456").getAsString());
			createOrUpdateAdminUser(0, adminUserModel);
		}
	}

	public User getUser(long uid) {
		return db.getById(User.class, uid);
	}

	private String formatPassword(String password) {
		if (StringUtils.isEmpty(password)) {
			return null;
		}

		return PASSWORD_ENCODER.encode(password);
	}

	public Result updateUserAttribute(long uid, UserAttributeModel userAttributeModel) {
		User user = getUser(uid);
		if (user == null) {
			return resultFactory.error("用户不存在");
		}

		if (userAttributeModel != null) {
			userAttributeModel.writeTo(user);
			db.update(user);
		}
		return resultFactory.success();
	}

	public Result checkPassword(long uid, String password) {
		if (StringUtils.isEmpty(password)) {
			return resultFactory.parameterError();
		}

		User user = getUser(uid);
		if (user == null) {
			return resultFactory.error("账号不存在");
		}

		if (!formatPassword(password).equals(user.getPassword())) {
			return resultFactory.error("账号或密码错误");
		}
		return resultFactory.success();
	}

	public Result updatePassword(long uid, String password) {
		if (StringUtils.isEmpty(password)) {
			return resultFactory.parameterError();
		}

		User user = getUser(uid);
		if (user == null) {
			return resultFactory.error("账号不存在");
		}

		user.setPassword(formatPassword(password));
		user.setLastUpdatePasswordTime(System.currentTimeMillis());
		db.update(user);
		return resultFactory.success();
	}

	public boolean isSuperAdmin(long uid) {
		User user = getUser(uid);
		if (user == null) {
			return false;
		}

		if (ADMIN_NAME.equals(user.getUsername())) {
			return true;
		}

		return false;
	}

	public Pagination<User> search(Collection<Integer> permissionGroupIds, String search, int page, int limit) {
		ConditionsBuilder builder = db.getMapper()
				.conditionsBuilder((e) -> e.name("permissionGroupId").greaterThan().value(0).build());
		if (!CollectionUtils.isEmpty(permissionGroupIds)) {
			builder.and((e) -> e.name("permissionGroupId").in().value(permissionGroupIds).build());
		}

		if (StringUtils.isNotEmpty(search)) {
			Conditions conditions = builder.newBuilder().and((e) -> e.name("uid").like().value(search).build())
					.and((e) -> e.name("phone").like().value(search).build())
					.and((e) -> e.name("username").like().value(search).build())
					.and((e) -> e.name("nickname").like().value(search).build()).build();
			builder.and(conditions);
		}
		return db.query(User.class, builder.build(), null).jumpToPage(page, limit);
	}

	public DataResult<User> createOrUpdateAdminUser(long uid, AdminUserModel adminUserModel) {
		if (StringUtils.isEmptyAny(adminUserModel.getUsername(), adminUserModel.getNickname())) {
			return resultFactory.error("参数错误");
		}

		User username = getUserByAccount(AccountType.USERNAME, adminUserModel.getUsername());
		User user = getUser(uid);
		if (user == null) {
			if (username != null) {
				return resultFactory.error("账号已经存在");
			}

			user = new User();
			if (StringUtils.isEmpty(adminUserModel.getPassword())) {
				return resultFactory.error("密码不能为空");
			}
			user.setPassword(formatPassword(adminUserModel.getPassword()));
			user.setUsername(adminUserModel.getUsername());
		} else {
			if (!adminUserModel.getUsername().equals(user.getUsername())) {
				if (username != null) {
					return resultFactory.error("账号已经存在");
				}

				user.setUsername(adminUserModel.getUsername());
			}

			if (StringUtils.isNotEmpty(adminUserModel.getPassword())) {
				user.setPassword(formatPassword(adminUserModel.getPassword()));
			}
		}

		user.setNickname(adminUserModel.getNickname());
		user.setDisable(adminUserModel.isDisable());
		user.setPermissionGroupId(adminUserModel.getGroupId());
		// TODO 没有原子性
		boolean b = db.saveOrUpdate(user);
		if (!b) {
			return resultFactory.error();
		}
		return resultFactory.success(user);
	}

	public Result updateDefaultAddressId(long uid, long addressId) {
		User user = getUser(uid);
		if (user == null) {
			return resultFactory.error("启用不存在");
		}

		user.setDefaultAddressId(addressId);
		db.update(user);
		return resultFactory.success();
	}

	public User getUserByAccount(AccountType type, String account) {
		return getUserByAccount(type, account, null);
	}

	public User getUserByAccount(AccountType type, String account, String password) {
		Sql sql;
		if (StringUtils.isNotEmpty(password)) {
			sql = new SimpleSql("select * from user where " + type.getFieldName() + "=? and password=?", account,
					password);
		} else {
			sql = new SimpleSql("select * from user where " + type.getFieldName() + "=?", account);
		}
		return db.query(User.class, sql).first();
	}

	public DataResult<User> register(AccountType accountType, String account, String password,
			UserAttributeModel userAttributeModel) {
		User user = getUserByAccount(accountType, account);
		if (user != null) {
			return resultFactory.error("账号已经存在");
		}

		user = new User();
		accountType.setAccount(user, account);
		user.setPassword(formatPassword(password));
		user.setCts(System.currentTimeMillis());

		if (userAttributeModel != null) {
			userAttributeModel.writeTo(user);
		}
		db.save(user);
		appEventDispatcher.publishEvent(User.class, new AppEvent<User>(user, EventTypes.CREATE));
		return resultFactory.success(user);
	}

	public DataResult<User> bind(long uid, AccountType accountType, String account) {
		User user = getUserByAccount(accountType, account);
		if (user != null) {
			return resultFactory.error("账号已被绑定");
		}

		user = getUser(uid);
		if (user == null) {
			return resultFactory.error("账号不存在");
		}

		if (account.equals(accountType.getAccount(user))) {
			return resultFactory.error("与原绑定信息一致");
		}

		accountType.setAccount(user, account);
		db.update(user);
		return resultFactory.success(user);
	}

	public DataResult<User> registerUnionId(int unionIdType, String unionId, String password,
			UserAttributeModel userAttributeModel) {
		UnionIdToUid unionIdToUid = getUidByUnionId(unionId, unionIdType);
		if (unionIdToUid != null) {
			return resultFactory.error("账号已经存在");
		}

		User user = new User();
		user.setPassword(formatPassword(password));
		user.setCts(System.currentTimeMillis());

		if (userAttributeModel != null) {
			userAttributeModel.writeTo(user);
		}
		db.save(user);

		unionIdToUid = new UnionIdToUid();
		unionIdToUid.setType(unionIdType);
		unionIdToUid.setUid(user.getUid());
		unionIdToUid.setUnionId(unionId);
		db.save(unionIdToUid);

		UidToUnionId uidToUnionId = new UidToUnionId();
		uidToUnionId.setUnionId(unionId);
		uidToUnionId.setType(unionIdType);
		uidToUnionId.setUid(user.getUid());
		db.save(unionIdToUid);

		appEventDispatcher.publishEvent(User.class, new AppEvent<User>(user, EventTypes.CREATE));
		return resultFactory.success(user);
	}

	public Result bindUnionId(long uid, int unionIdType, String unionId) {
		UnionIdToUid unionIdToUid = getUidByUnionId(unionId, unionIdType);
		if (unionIdToUid != null) {
			return resultFactory.error("账号已被绑定");
		}

		unionIdToUid = new UnionIdToUid();
		unionIdToUid.setUid(uid);
		unionIdToUid.setType(unionIdType);
		unionIdToUid.setUnionId(unionId);
		db.save(unionIdToUid);

		UidToUnionId uidToUnionId = getUnionIdByUid(uid, unionIdType);
		if (uidToUnionId == null) {
			uidToUnionId = new UidToUnionId();
			uidToUnionId.setUid(uid);
			uidToUnionId.setType(unionIdType);
			uidToUnionId.setUnionId(unionId);
			db.save(uidToUnionId);
		} else {
			if (uidToUnionId.getUnionId().equals(unionId)) {
				return resultFactory.error("与原绑定信息一致");
			}

			UnionIdToUid old = getUidByUnionId(uidToUnionId.getUnionId(), unionIdType);
			if (old != null) {
				db.delete(old);
			}

			uidToUnionId.setUnionId(unionId);
			db.update(uidToUnionId);
		}
		return resultFactory.success();
	}

	public User getUserByUnionId(String unionId, UnionIdType type) {
		return getUserByUnionId(unionId, type.getValue());
	}

	public User getUserByUnionId(String unionId, int type) {
		UnionIdToUid unionIdToUid = getUidByUnionId(unionId, type);
		return unionIdToUid == null ? null : getUser(unionIdToUid.getUid());
	}

	public UidToUnionId getUnionIdByUid(long uid, int type) {
		return db.getById(UidToUnionId.class, uid, type);
	}

	public UnionIdToUid getUidByUnionId(String unionId, int type) {
		return db.getById(UnionIdToUid.class, unionId, type);
	}

	public DataResult<User> registerUnionId(UnionIdType type, String unionId, String password,
			UserAttributeModel userAttributeModel) {
		return registerUnionId(type.getValue(), unionId, password, userAttributeModel);
	}

	public Result bindUnionId(long uid, UnionIdType type, String unionId) {
		return bindUnionId(uid, type.getValue(), unionId);
	}

	public UidToUnionId getUnionIdByUid(long uid, UnionIdType type) {
		return getUnionIdByUid(uid, type.getValue());
	}

	public UnionIdToUid getUidByUnionId(String unionId, UnionIdType type) {
		return getUidByUnionId(unionId, type.getValue());
	}

	@Override
	public List<User> getUsers(List<Long> uids) {
		return db.getInIds(User.class, uids).toList();
	}
}
