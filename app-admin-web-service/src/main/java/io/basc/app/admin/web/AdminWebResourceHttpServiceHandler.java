package io.basc.app.admin.web;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.web.resource.DefaultStaticResourceLoader;
import io.basc.framework.web.resource.StaticResourceHttpService;

@Provider
public class AdminWebResourceHttpServiceHandler extends StaticResourceHttpService {

	public AdminWebResourceHttpServiceHandler() {
		DefaultStaticResourceLoader resourceLoader = new DefaultStaticResourceLoader();
		resourceLoader.addMapping("/", "/admin/js/**", "/admin/css/**", "/admin/fonts/**", "/admin/images/**",
				"/admin/lib/**");
		setResourceLoader(resourceLoader);
	}
}
