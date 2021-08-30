package io.basc.satrt.admin.web;

import io.basc.framework.beans.annotation.Autowired;
import io.basc.framework.context.result.Result;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.core.utils.CollectionUtils;
import io.basc.framework.core.utils.StringUtils;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mapper.Field;
import io.basc.framework.mapper.MapperUtils;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.mvc.annotation.Controller;
import io.basc.framework.mvc.security.HttpActionAuthorityManager;
import io.basc.framework.mvc.view.Redirect;
import io.basc.framework.mvc.view.View;
import io.basc.framework.security.authority.AuthorityTree;
import io.basc.framework.security.authority.MenuAuthorityFilter;
import io.basc.framework.security.authority.http.HttpAuthority;
import io.basc.framework.security.session.UserSession;
import io.basc.framework.web.ServerHttpRequest;
import io.basc.framework.web.model.Page;
import io.basc.start.user.enums.AccountType;
import io.basc.start.user.pojo.PermissionGroupAction;
import io.basc.start.user.pojo.User;
import io.basc.start.user.security.LoginRequired;
import io.basc.start.user.security.SecurityProperties;
import io.basc.start.user.security.UserLoginService;
import io.basc.start.user.service.PermissionGroupActionService;
import io.basc.start.user.service.UserService;

import java.util.List;
import java.util.Map;

@Controller(value = SecurityProperties.ADMIN_CONTROLLER)
public class AdminIndexController {
	private UserService userService;
	@Autowired
	private ResultFactory resultFactory;
	@Autowired
	private HttpActionAuthorityManager httpActionAuthorityManager;
	private PermissionGroupActionService permissionGroupActionService;
	@Autowired
	private UserLoginService userLoginService;
	@Autowired
	private SecurityProperties securityConfigProperties;

	public AdminIndexController(UserService userService, PermissionGroupActionService permissionGroupActionService) {
		this.userService = userService;
		this.permissionGroupActionService = permissionGroupActionService;
	}

	@LoginRequired
	@Controller(value = "menus")
	@io.basc.framework.mvc.annotation.FactoryResult
	public List<AuthorityTree<HttpAuthority>> getMenus(UserSession<Long> requestUser) {
		if (userService.isSuperAdmin(requestUser.getUid())) {
			return httpActionAuthorityManager.getAuthorityTreeList(new MenuAuthorityFilter<HttpAuthority>());
		} else {
			User user = userService.getUser(requestUser.getUid());
			if (user == null) {
				throw new RuntimeException("用户不存在");
			}
			List<PermissionGroupAction> actions = permissionGroupActionService
					.getActionList(user.getPermissionGroupId());
			Field field = MapperUtils.getFields(PermissionGroupAction.class).all().find("actionId", null);
			List<String> actionIds = field.getValues(actions);
			return httpActionAuthorityManager.getRelationAuthorityTreeList(actionIds,
					new MenuAuthorityFilter<HttpAuthority>());
		}
	}
	
	@Controller(value = "login", methods=HttpMethod.POST)
	public Result login(String username, String password, HttpChannel httpChannel) {
		if (StringUtils.isEmpty(username, password)) {
			return resultFactory.parameterError();
		}

		User user = userService.getUserByAccount(AccountType.USERNAME, username);
		if (user == null) {
			user = userService.getUserByAccount(AccountType.PHONE, username);
		}

		if (user == null) {
			return resultFactory.error("账号或密码错误");
		}

		Result result = userService.checkPassword(user.getUid(), password);
		if (result.isError()) {
			return result;
		}

		Map<String, Object> infoMap = userLoginService.login(user, httpChannel);
		return resultFactory.success(infoMap);
	}

	@Controller
	@LoginRequired
	public Page index(UserSession<Long> requestUser, ServerHttpRequest request) {
		Page page = new Page("/admin/ftl/index.ftl");
		StringBuilder sb = new StringBuilder(4096);
		appendMenuHtml(sb, getMenus(requestUser), request.getContextPath());
		page.put("leftHtml", sb.toString());
		page.put("admin", userService.getUser(requestUser.getUid()));
		return page;
	}

	private void appendMenuHtml(StringBuilder sb, List<AuthorityTree<HttpAuthority>> authorityTrees,
			String contextPath) {
		if (authorityTrees == null || authorityTrees.isEmpty()) {
			return;
		}

		for (AuthorityTree<HttpAuthority> actionResult : authorityTrees) {
			HttpAuthority httpAuthority = actionResult.getAuthority();
			boolean isSub = !CollectionUtils.isEmpty(actionResult.getSubList());
			sb.append("<li>");
			sb.append("<a ");
			if (isSub) {
				sb.append("href='javascript:;'");
			} else {
				sb.append("_href='");
				sb.append(contextPath + httpAuthority.getPath());
				sb.append("'");
			}
			sb.append(">");
			String icon = httpAuthority.getAttributeMap().get(AdminConstants.ICON_ATTR_NAME);
			if (StringUtils.isNotEmpty(icon)) {
				sb.append("<i class='iconfont'>").append(icon).append("</i>");
			}
			sb.append("<cite>").append(httpAuthority.getName()).append("</cite>");
			if (isSub) {
				sb.append("<i class='iconfont nav_right'>&#xe697;</i>");
			}
			sb.append("</a>");
			if (isSub) {
				sb.append("<ul class='sub-menu'>");
				appendMenuHtml(sb, actionResult.getSubList(), contextPath);
				sb.append("</ul>");
			}
			sb.append("</li>");
		}
	}

	@Controller(value = "login")
	public Object login(HttpChannel httpChannel) {
		UserSession<Long> userSession = httpChannel.getUserSession(Long.class);
		if(userSession != null){
			return new Redirect(httpChannel.getRequest().getContextPath() + securityConfigProperties.getController());
		}
		return new Page("/admin/ftl/login.ftl");
	}

	@LoginRequired
	@Controller(value = "welcome")
	public Page welcome() {
		return new Page("/admin/ftl/welcome.ftl");
	}

	@LoginRequired
	@Controller(value = "update_pwd")
	public Page update_pwd() {
		return new Page("/admin/ftl/update_pwd.ftl");
	}

	@LoginRequired
	@Controller(value = "update_pwd", methods = HttpMethod.POST)
	public Result update_pwd(UserSession<Long> requestUser, String oldPwd, String newPwd) {
		if (StringUtils.isEmpty(oldPwd, newPwd)) {
			return resultFactory.parameterError();
		}

		Result result = userService.checkPassword(requestUser.getUid(), oldPwd);
		if (result.isError()) {
			return resultFactory.error("旧密码错误");
		}

		return userService.updatePassword(requestUser.getUid(), newPwd);
	}

	@Controller(value = "cancel_login")
	public View cacelLogin(UserSession<Long> requestUser, ServerHttpRequest request) {
		requestUser.invalidate();
		return new Redirect(request.getContextPath() + securityConfigProperties.getToLoginPath());
	}

	@Controller(value = "to_login")
	public Page toLogin() {
		return new Page("/admin/ftl/to_login.ftl");
	}
}
