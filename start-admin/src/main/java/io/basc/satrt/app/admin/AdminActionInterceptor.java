package io.basc.satrt.app.admin;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.context.ioc.annotation.Autowired;
import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.env.Sys;
import io.basc.framework.event.Observable;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.mvc.action.Action;
import io.basc.framework.mvc.action.ActionInterceptor;
import io.basc.framework.mvc.action.ActionInterceptorChain;
import io.basc.framework.mvc.action.ActionParameters;
import io.basc.framework.web.message.model.ModelAndView;

@Provider
public class AdminActionInterceptor implements ActionInterceptor {
	public static final Observable<String> ADMIN_WEBSITE_NAME = Sys.getEnv().getProperties()
			.getObservable("admin.website.name").map((e) -> e.or("后台管理系统").getAsString());

	@Autowired
	private ResultFactory resultFactory;

	public Object intercept(HttpChannel httpChannel, Action action, ActionParameters parameters,
			ActionInterceptorChain chain) throws Throwable {
		Object value = chain.intercept(httpChannel, action, parameters);
		if (value instanceof ModelAndView) {
			ModelAndView page = (ModelAndView) value;
			page.put("adminWebsiteName", ADMIN_WEBSITE_NAME.get());
			return httpChannel.getRequest().getHeaders().isAjax() ? resultFactory.success(page) : page;
		}
		return value;
	}

}
