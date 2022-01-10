package io.basc.start.app.user.security;

import java.util.ArrayList;
import java.util.List;

import io.basc.framework.beans.annotation.Autowired;
import io.basc.framework.context.annotation.Provider;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.core.annotation.AnnotationUtils;
import io.basc.framework.logger.Logger;
import io.basc.framework.logger.LoggerFactory;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.mvc.action.Action;
import io.basc.framework.mvc.action.ActionInterceptor;
import io.basc.framework.mvc.action.ActionInterceptorAccept;
import io.basc.framework.mvc.action.ActionInterceptorChain;
import io.basc.framework.mvc.action.ActionParameters;
import io.basc.framework.mvc.annotation.ActionAuthority;
import io.basc.framework.mvc.security.HttpActionAuthorityManager;
import io.basc.framework.mvc.view.Redirect;
import io.basc.framework.security.authority.http.HttpAuthority;
import io.basc.framework.security.session.UserSession;
import io.basc.framework.util.StringUtils;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.app.user.pojo.PermissionGroup;
import io.basc.start.app.user.pojo.User;
import io.basc.start.app.user.service.PermissionGroupActionService;
import io.basc.start.app.user.service.PermissionGroupService;
import io.basc.start.app.user.service.UserService;

@Provider
public class SecurityActionInterceptor implements ActionInterceptor, ActionInterceptorAccept {
	private static Logger logger = LoggerFactory.getLogger(SecurityActionInterceptor.class);

	@Autowired
	private AppConfigure appConfigure;

	@Autowired(required = false)
	private ResultFactory resultFactory;
	@Autowired(required = false)
	private HttpActionAuthorityManager httpActionAuthorityManager;
	@Autowired(required = false)
	private LoginRequiredRegistry loginRequiredRegistry;

	@Autowired(required = false)
	private PermissionGroupActionService permissionGroupActionService;
	@Autowired(required = false)
	private PermissionGroupService permissionGroupService;
	@Autowired(required = false)
	private UserService userService;

	private boolean isSupported() {
		return resultFactory != null && httpActionAuthorityManager != null && loginRequiredRegistry != null
				&& userService != null && permissionGroupActionService != null && permissionGroupService != null;
	}

	private Object getUnsupportedDesc() {
		return new Object() {
			@Override
			public String toString() {
				List<Object> list = new ArrayList<Object>(8);
				if (resultFactory == null) {
					list.add(ResultFactory.class.getName());
				}

				if (httpActionAuthorityManager == null) {
					list.add(HttpActionAuthorityManager.class);
				}

				if (loginRequiredRegistry == null) {
					list.add(LoginRequiredRegistry.class);
				}

				if (permissionGroupActionService == null) {
					list.add(PermissionGroupActionService.class);
				}

				if (permissionGroupService == null) {
					list.add(PermissionGroupService.class);
				}

				if (userService == null) {
					list.add(UserService.class);
				}
				return "@Autowired fail " + StringUtils.collectionToCommaDelimitedString(list);
			}
		};
	}

	private boolean loginRequired(LoginRequired loginRequired, HttpChannel httpChannel) {
		return httpActionAuthorityManager.getAuthority(httpChannel.getRequest().getPath(),
				httpChannel.getRequest().getMethod()) != null || (loginRequired != null && loginRequired.value());
	}

	public boolean isAccept(HttpChannel httpChannel, Action action, ActionParameters parameters) {
		LoginRequired required = AnnotationUtils.getAnnotation(LoginRequired.class, action.getSourceClass(), action);
		return loginRequired(required, httpChannel)
				|| (loginRequiredRegistry == null || loginRequiredRegistry.isLoginRequried(httpChannel.getRequest()));
	}

	public Object intercept(HttpChannel httpChannel, Action action, ActionParameters parameters,
			ActionInterceptorChain chain) throws Throwable {
		LoginRequired required = AnnotationUtils.getAnnotation(LoginRequired.class, action.getSourceClass(), action);
		ActionAuthority actionAuthority = action.getAnnotation(ActionAuthority.class);
		boolean loginRequired = loginRequired(required, httpChannel);
		if (loginRequired && !isSupported()) {
			logger.warn("Authentication is required, but authentication service is not supported! {}, {}",
					getUnsupportedDesc(), httpChannel);
			return resultFactory.error("Authentication is required, but authentication service is not supported");
		}

		if (loginRequired || loginRequiredRegistry.isLoginRequried(httpChannel.getRequest())) {
			UserSession<Long> userSession = httpChannel.getUserSession(Long.class);
			if (userSession == null) {
				return authorizationFailure(httpChannel, action);
			}

			if (httpChannel.getRequest().getPath().startsWith(appConfigure.getAdminController())) {
				User user = userService.getUser(userSession.getUid());
				if (user == null) {
					return authorizationFailure(httpChannel, action);
				}
			}

			HttpAuthority authority = httpActionAuthorityManager.getAuthority(httpChannel.getRequest().getPath(),
					httpChannel.getRequest().getMethod());
			if (actionAuthority != null || authority != null) {
				if (!userService.isSuperAdmin(userSession.getUid())) {
					if (authority == null) {
						return error(httpChannel, action, resultFactory.error("系统错误，权限不足"));
					}

					User user = userService.getUser(userSession.getUid());
					if (user == null) {
						return authorizationFailure(httpChannel, action);
					}

					if (user.isDisable()) {
						return error(httpChannel, action, resultFactory.error("账号已禁用，如有问题请联系管理员!"));
					}

					PermissionGroup group = permissionGroupService.getById(user.getPermissionGroupId());
					if (group == null) {
						return error(httpChannel, action, resultFactory.error("权限不足(1)"));
					}

					if (group.isDisable()) {
						return error(httpChannel, action, resultFactory.error("账号分组已禁用，如有问题请联系管理员!"));
					}

					if (!permissionGroupActionService.check(user.getPermissionGroupId(), authority.getId())) {
						return error(httpChannel, action, resultFactory.error("权限不足"));
					}
				}
			}
		}
		return chain.intercept(httpChannel, action, parameters);
	}

	protected Object authorizationFailure(HttpChannel httpChannel, Action action) throws Throwable {
		if (httpChannel.getRequest().getPath().startsWith(appConfigure.getAdminController())) {
			if (!httpChannel.getRequest().getHeaders().isAjax()) {
				return new Redirect(appConfigure.getToAdminLoginPath());
			}
		}
		return resultFactory.authorizationFailure();
	}

	protected Object error(HttpChannel httpChannel, Action action, Object result) {
		return result;
	}

}
