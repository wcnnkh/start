package io.basc.start.app.user.service;

import io.basc.framework.context.result.Result;
import io.basc.framework.util.page.Pagination;
import io.basc.start.app.user.pojo.UserTag;

public interface UserTagService {
	/**
	 * 绑定标签
	 * 
	 * @param userTag
	 * @return
	 */
	Result bind(long uid, String tag, String describe);

	/**
	 * 移除标签
	 * 
	 * @param uid
	 * @param tag
	 * @return
	 */
	Result remove(long uid, String tag);
	
	/**
	 * 获取用户的指定标签
	 * @param uid
	 * @param tag
	 * @return
	 */
	UserTag getUserTag(long uid, String tag);

	/**
	 * 获取用户的所有标签
	 * 
	 * @param uid
	 * @param page
	 * @param limit
	 * @return
	 */
	Pagination<UserTag> getUserTags(long uid, int page, int limit);

	/**
	 * 获取此标签的用户
	 * 
	 * @param tag
	 * @param page
	 * @param limit
	 * @return
	 */
	Pagination<UserTag> getUserTags(String tag, int page, int limit);
}
