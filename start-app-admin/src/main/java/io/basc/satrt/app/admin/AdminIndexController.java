package io.basc.satrt.app.admin;

import io.basc.framework.beans.annotation.Autowired;
import io.basc.framework.context.result.Result;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mapper.Field;
import io.basc.framework.mapper.MapperUtils;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.mvc.security.HttpActionAuthorityManager;
import io.basc.framework.mvc.view.Redirect;
import io.basc.framework.mvc.view.View;
import io.basc.framework.security.authority.AuthorityTree;
import io.basc.framework.security.authority.MenuAuthorityFilter;
import io.basc.framework.security.authority.http.HttpAuthority;
import io.basc.framework.security.login.UserToken;
import io.basc.framework.security.session.UserSession;
import io.basc.framework.util.CollectionUtils;
import io.basc.framework.util.StringUtils;
import io.basc.framework.web.ServerHttpRequest;
import io.basc.framework.web.message.model.ModelAndView;
import io.basc.framework.web.pattern.annotation.RequestMapping;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.app.user.enums.AccountType;
import io.basc.start.app.user.pojo.PermissionGroupAction;
import io.basc.start.app.user.pojo.User;
import io.basc.start.app.user.security.LoginRequired;
import io.basc.start.app.user.security.UserLoginService;
import io.basc.start.app.user.service.PermissionGroupActionService;
import io.basc.start.app.user.service.UserService;

import java.util.List;
import java.util.Map;

@RequestMapping(value = AppConfigure.ADMIN_CONTROLLER)
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
	private AppConfigure appConfigure;

	public AdminIndexController(UserService userService, PermissionGroupActionService permissionGroupActionService) {
		this.userService = userService;
		this.permissionGroupActionService = permissionGroupActionService;
	}

	@LoginRequired
	@RequestMapping(value = "menus")
	@io.basc.framework.mvc.annotation.FactoryResult
	public List<AuthorityTree<HttpAuthority>> getMenus(UserToken<Long> requestUser) {
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
	
	@RequestMapping(value = "login", methods=HttpMethod.POST)
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

	@RequestMapping
	@LoginRequired
	public ModelAndView index(UserToken<Long> requestUser, ServerHttpRequest request) {
		ModelAndView page = new ModelAndView("/io/basc/start/app/admin/web/ftl/index.ftl");
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

	@RequestMapping(value = "login")
	public Object login(HttpChannel httpChannel) {
		UserSession<Long> userSession = httpChannel.getUserSession(Long.class);
		if(userSession != null){
			return new Redirect(httpChannel.getRequest().getContextPath() + appConfigure.getAdminController());
		}
		return new ModelAndView("/io/basc/start/app/admin/web/ftl/login.ftl");
	}

	@LoginRequired
	@RequestMapping(value = "welcome")
	public ModelAndView welcome() {
		return new ModelAndView("/io/basc/start/app/admin/web/ftl/welcome.ftl");
	}

	@LoginRequired
	@RequestMapping(value = "update_pwd")
	public ModelAndView update_pwd() {
		return new ModelAndView("/io/basc/start/app/admin/web/ftl/update_pwd.ftl");
	}

	@LoginRequired
	@RequestMapping(value = "update_pwd", methods = HttpMethod.POST)
	public Result update_pwd(UserToken<Long> requestUser, String oldPwd, String newPwd) {
		if (StringUtils.isEmpty(oldPwd, newPwd)) {
			return resultFactory.parameterError();
		}

		Result result = userService.checkPassword(requestUser.getUid(), oldPwd);
		if (result.isError()) {
			return resultFactory.error("旧密码错误");
		}

		return userService.updatePassword(requestUser.getUid(), newPwd);
	}

	@RequestMapping(value = "cancel_login")
	public View cacelLogin(UserSession<Long> requestUser, ServerHttpRequest request) {
		requestUser.invalidate();
		return new Redirect(request.getContextPath() + appConfigure.getToAdminLoginPath());
	}

	@RequestMapping(value = "to_login")
	public ModelAndView toLogin() {
		return new ModelAndView("/io/basc/start/app/admin/web/ftl/to_login.ftl");
	}
}
