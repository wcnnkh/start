package io.basc.start.app.user.security;

import io.basc.framework.web.ServerHttpRequest;
import io.basc.framework.web.pattern.HttpPatternMatcher;

public class LoginRequiredRegistry extends HttpPatternMatcher<Boolean> {

	public boolean isLoginRequried(ServerHttpRequest request) {
		Boolean v = get(request);
		if (v == null) {
			return false;
		}
		return v;
	}
}
