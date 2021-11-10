package io.basc.satrt.app.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.basc.framework.beans.annotation.Autowired;
import io.basc.framework.context.result.Result;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.annotation.ActionAuthority;
import io.basc.framework.mvc.annotation.ActionAuthorityParent;
import io.basc.framework.mvc.security.HttpActionAuthorityManager;
import io.basc.framework.security.authority.http.HttpAuthority;
import io.basc.framework.security.login.UserToken;
import io.basc.framework.util.StringUtils;
import io.basc.framework.web.message.model.ModelAndView;
import io.basc.framework.web.pattern.annotation.RequestMapping;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.app.user.model.PermissionGroupInfo;
import io.basc.start.app.user.pojo.PermissionGroup;
import io.basc.start.app.user.pojo.PermissionGroupAction;
import io.basc.start.app.user.pojo.User;
import io.basc.start.app.user.security.LoginRequired;
import io.basc.start.app.user.service.PermissionGroupActionService;
import io.basc.start.app.user.service.PermissionGroupService;
import io.basc.start.app.user.service.UserService;

@LoginRequired
@ActionAuthorityParent(AdminUserController.class)
@RequestMapping(value = AppConfigure.ADMIN_CONTROLLER)
public class PermissionGroupController {
	private PermissionGroupService permissionGroupService;
	private PermissionGroupActionService permissionGroupActionService;
	@Autowired
	private ResultFactory resultFactory;
	private UserService userService;
	@Autowired
	private HttpActionAuthorityManager httpActionAuthorityManager;

	public PermissionGroupController(PermissionGroupService permissionGroupService,
			PermissionGroupActionService permissionGroupActionService, UserService userService) {
		this.permissionGroupService = permissionGroupService;
		this.permissionGroupActionService = permissionGroupActionService;
		this.userService = userService;
	}

	@ActionAuthority(value = "权限组列表", menu = true)
	@RequestMapping(value = "group_list")
	public ModelAndView group_list(UserToken<Long> requestUser, Integer parentId) {
		User user = userService.getUser(requestUser.getUid());
		int pid = (parentId == null || parentId == 0) ? user.getPermissionGroupId() : parentId;
		ModelAndView page = new ModelAndView("/io/basc/start/app/admin/web/ftl/group_list.ftl");
		page.put("groupList", permissionGroupService.getSubList(pid, false));
		page.put("parentId", pid);
		page.put("parentGroup", permissionGroupService.getById(pid));
		page.put("isTop", user.getPermissionGroupId() == pid);// 是否是最上级了
		return page;
	}

	@ActionAuthority(value = "(查看或修改)权限界面")
	@RequestMapping(value = "group_view")
	public Object group_view(int parentId, int id) {
		ModelAndView page = new ModelAndView("/io/basc/start/app/admin/web/ftl/group_view.ftl");
		page.put("parentId", parentId);
		page.put("group", permissionGroupService.getById(id));
		return page;
	}

	@LoginRequired
	@RequestMapping(value = "group_action_list")
	public Result action_list(int groupId, UserToken<Long> requestUser, int parentId) {
		List<HttpAuthority> httpAuthorities;
		if (parentId == 0 && userService.isSuperAdmin(requestUser.getUid())) {
			httpAuthorities = httpActionAuthorityManager.getAuthorityList(null);
		} else {
			User user = userService.getUser(requestUser.getUid());
			int id = parentId == 0 ? user.getPermissionGroupId() : parentId;
			List<PermissionGroupAction> actions = permissionGroupActionService.getActionList(id);
			httpAuthorities = new ArrayList<HttpAuthority>();
			for (PermissionGroupAction action : actions) {
				HttpAuthority httpAuthority = httpActionAuthorityManager.getAuthority(action.getActionId());
				if (httpAuthority == null) {
					continue;
				}

				httpAuthorities.add(httpAuthority);
			}
		}

		HashSet<String> selecteds = new HashSet<String>();
		List<PermissionGroupAction> actions = permissionGroupActionService.getActionList(groupId);
		for (PermissionGroupAction action : actions) {
			selecteds.add(action.getActionId());
		}

		List<Object> list = new ArrayList<Object>();
		for (HttpAuthority httpAuthority : httpAuthorities) {
			Map<String, Object> map = new HashMap<String, Object>(8);
			map.put("id", httpAuthority.getId());
			map.put("pId", httpAuthority.getParentId());
			map.put("name", httpAuthority.getName());
			map.put("checked", selecteds.contains(httpAuthority.getId()));
			list.add(map);
		}
		return resultFactory.success(list);
	}

	@ActionAuthority(value = "(添加/修改)权限")
	@RequestMapping(value = "group_add_or_update", methods = HttpMethod.POST)
	public Result group_add_or_update(UserToken<Long> requestUser, int id, int parentId, String name, boolean disable,
			String ids) {
		PermissionGroupInfo info = new PermissionGroupInfo();
		info.setId(id);
		info.setDisable(disable);
		info.setAuthorityIds(StringUtils.split(ids, ",").map((s) -> s.toString()).collect(Collectors.toList()));
		info.setName(name);

		PermissionGroup group = permissionGroupService.getById(id);
		if (group == null) {
			info.setParentId(parentId);
		} else {
			info.setParentId(group.getParentId());
		}
		return permissionGroupService.createOrUpdate(info);
	}
}
