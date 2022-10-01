package io.basc.satrt.app.admin;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.env.Environment;
import io.basc.framework.io.Resource;
import io.basc.framework.web.ServerHttpRequest;
import io.basc.framework.web.resource.StaticResourceLoader;

@Provider
public class AdminStaticResourceLoader implements StaticResourceLoader {
	private static final String[] STATIC_PREFIXS = new String[] { "/js/", "/css/", "/fonts/", "/images/", "/lib/" };
	private final Environment environment;

	public AdminStaticResourceLoader(Environment environment) {
		this.environment = environment;
	}

	@Override
	public Resource getResource(ServerHttpRequest request) {
		String path = request.getPath();
		for (String prefix : STATIC_PREFIXS) {
			if (path.startsWith("/admin" + prefix)) {
				String resourcePath = "classpath:/io/basc/start/app/admin/web/static/" + path.substring(6);
				return environment.getResourceLoader().getResource(resourcePath);
			}
		}
		return null;
	}
}
