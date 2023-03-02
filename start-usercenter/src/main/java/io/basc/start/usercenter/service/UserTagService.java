package io.basc.start.usercenter.service;

import io.basc.framework.context.transaction.Result;
import io.basc.framework.util.page.Pagination;
import io.basc.start.usercenter.pojo.UserTag;

public interface UserTagService {
	Result bind(long uid, String tag, String describe);

	Result remove(long uid, String tag);

	UserTag getUserTag(long uid, String tag);

	Pagination<UserTag> getUserTags(long uid, int page, int limit);

	Pagination<UserTag> getUserTags(String tag, int page, int limit);
}
