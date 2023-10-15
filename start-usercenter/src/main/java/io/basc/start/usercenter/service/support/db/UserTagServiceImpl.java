package io.basc.start.usercenter.service.support.db;

import io.basc.framework.context.annotation.Service;
import io.basc.framework.context.transaction.Result;
import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.db.Database;
import io.basc.framework.jdbc.SimpleSql;
import io.basc.framework.jdbc.Sql;
import io.basc.framework.util.page.Pagination;
import io.basc.start.template.service.impl.TemplateServiceSupport;
import io.basc.start.usercenter.pojo.UserTag;
import io.basc.start.usercenter.service.UserTagService;

@Service
public class UserTagServiceImpl extends TemplateServiceSupport implements UserTagService {

	public UserTagServiceImpl(Database db, ResultFactory resultFactory) {
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
		return db.query(UserTag.class, sql).jumpToPage(page, limit);
	}

	@Override
	public Pagination<UserTag> getUserTags(String tag, int page, int limit) {
		Sql sql = new SimpleSql("select * from user_tag where tag=?", tag);
		return db.query(UserTag.class, sql).jumpToPage(page, limit);
	}

	@Override
	public UserTag getUserTag(long uid, String tag) {
		return db.getById(UserTag.class, uid, tag);
	}

}
