package io.basc.satrt.admin.web;

import io.basc.framework.context.result.Result;
import io.basc.framework.core.utils.CollectionUtils;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.lang.DefaultValue;
import io.basc.framework.mapper.Field;
import io.basc.framework.mapper.Fields;
import io.basc.framework.mapper.MapperUtils;
import io.basc.framework.mvc.annotation.ActionAuthority;
import io.basc.framework.mvc.annotation.Controller;
import io.basc.framework.security.session.UserSession;
import io.basc.framework.util.page.PageSupport;
import io.basc.framework.web.message.annotation.RequestBody;
import io.basc.framework.web.model.Page;
import io.basc.start.user.model.AdminUserModel;
import io.basc.start.user.pojo.PermissionGroup;
import io.basc.start.user.pojo.User;
import io.basc.start.user.security.LoginRequired;
import io.basc.start.user.security.SecurityProperties;
import io.basc.start.user.service.PermissionGroupService;
import io.basc.start.user.service.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@LoginRequired
@ActionAuthority(value = "系统设置", menu = true)
@Controller(value = SecurityProperties.ADMIN_CONTROLLER)
public class AdminUserController {
	private UserService userService;
	private PermissionGroupService permissionGroupService;

	public AdminUserController(UserService userService, PermissionGroupService permissionGroupService) {
		this.userService = userService;
		this.permissionGroupService = permissionGroupService;
	}

	@ActionAuthority(value = "管理员列表", menu = true)
	@Controller(value = "admin_list")
	public Page admin_list(UserSession<Long> requestUser, Integer groupId, @DefaultValue("1") int page, String search,
			@DefaultValue("10") int limit) {
		User currentUser = userService.getUser(requestUser.getUid());
		List<PermissionGroup> userSubGroups = permissionGroupService.getSubList(currentUser.getPermissionGroupId(),
				true);
		List<Integer> groupIds = null;// groupIds如果为空就表示没有数据，如果长度为0就表示全部
		Field groupIdField = MapperUtils.getFields(PermissionGroup.class).all().find("id", null);
		if (groupId == null) {// 全部
			if (userService.isSuperAdmin(requestUser.getUid())) {
				groupIds = Collections.emptyList();
			} else {
				if (!CollectionUtils.isEmpty(userSubGroups)) {
					groupIds = groupIdField.getValues(userSubGroups);
				}
				// else 该分组下没有子分组了
			}
		} else {
			// 如果选择了分组
			List<PermissionGroup> groups = permissionGroupService.getSubList(groupId, true);
			if (!CollectionUtils.isEmpty(groups)) {
				groupIds = groupIdField.getValues(groups);
			}
			// else 该分组下没有子分组了
		}

		io.basc.framework.util.page.Page<User> pagination;
		if (groupIds == null) {
			pagination = PageSupport.emptyPage(page, limit);
		} else {
			pagination = userService.search(groupIds, search, page, limit);
		}

		List<Object> list = new ArrayList<Object>();
		Fields fields = MapperUtils.getFields(User.class).entity().all().shared();
		for (User user : pagination.rows()) {
			PermissionGroup group = permissionGroupService.getById(user.getPermissionGroupId());
			String groupName = group == null ? "系统分组" : group.getName();
			boolean groupDisable = group == null ? false : group.isDisable();
			Map<String, Object> map = fields.getValueMap(user);
			map.put("groupName", groupName + "(状态：" + (!groupDisable ? "可用" : "禁用") + ")");
			list.add(map);
		}

		Page view = new Page("/admin/ftl/admin_list.ftl");
		view.put("adminList", list);
		view.put("page", page);
		view.put("maxPage", pagination.getPages());
		view.put("totalCount", pagination.getTotal());
		view.put("groupId", groupId);
		view.put("search", search);
		view.put("groupList", userSubGroups);
		return view;
	}

	@ActionAuthority(value = "(查看/修改)管理员信息界面")
	@Controller(value = "admin_view")
	public Object admin_view(long toUid, UserSession<Long> requestUser) {
		Page page = new Page("/admin/ftl/admin_view.ftl");
		User user = userService.getUser(toUid);
		User currentUser = userService.getUser(requestUser.getUid());
		List<PermissionGroup> groups = permissionGroupService.getSubList(currentUser.getPermissionGroupId(), true);
		page.put("groupList", groups);
		page.put("admin", user);
		return page;
	}

	@ActionAuthority(value = "(查看/修改)管理员信息")
	@Controller(value = "admin_create_or_update", methods = HttpMethod.POST)
	public Result admin_view(UserSession<Long> requestUser, long toUid, @RequestBody AdminUserModel adminUserModel) {
		return userService.createOrUpdateAdminUser(toUid, adminUserModel);
	}
}
