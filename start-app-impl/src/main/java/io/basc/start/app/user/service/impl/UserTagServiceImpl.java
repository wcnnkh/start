package io.basc.start.app.user.service.impl;

import io.basc.framework.beans.annotation.Service;
import io.basc.framework.context.result.Result;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.db.DB;
import io.basc.framework.sql.SimpleSql;
import io.basc.framework.sql.Sql;
import io.basc.framework.util.page.Pagination;
import io.basc.start.app.configure.BaseServiceConfiguration;
import io.basc.start.app.user.pojo.UserTag;
import io.basc.start.app.user.service.UserTagService;

@Service
public class UserTagServiceImpl extends BaseServiceConfiguration implements UserTagService {

	public UserTagServiceImpl(DB db, ResultFactory resultFactory) {
		super(db, resultFactory);
		db.createTable(UserTag.class, false);
	}

	@Override
	public Result bind(long uid, String tag, String describe) {
		UserTag userTag = getUserTag(uid, tag);
		if (userTag != null) {
			return resultFactory.error("标签已经存在");
		}

		userTag = new UserTag();
		userTag.setUid(uid);
		userTag.setTag(tag);
		userTag.setDescribe(describe);
		userTag.setCts(System.currentTimeMillis());
		db.save(userTag);
		return resultFactory.success();
	}

	@Override
	public Result remove(long uid, String tag) {
		UserTag userTag = getUserTag(uid, tag);
		if (userTag == null) {
			return resultFactory.error("标签不存在");
		}
		return resultFactory.success();
	}

	@Override
	public Pagination<UserTag> getUserTags(long uid, int page, int limit) {
		Sql sql = new SimpleSql("select * from user_tag where uid=?", uid);
		return db.getPage(UserTag.class, sql, page, limit);
	}

	@Override
	public Pagination<UserTag> getUserTags(String tag, int page, int limit) {
		Sql sql = new SimpleSql("select * from user_tag where tag=?", tag);
		return db.getPage(UserTag.class, sql, page, limit);
	}

	@Override
	public UserTag getUserTag(long uid, String tag) {
		return db.getById(UserTag.class, uid, tag);
	}

}
