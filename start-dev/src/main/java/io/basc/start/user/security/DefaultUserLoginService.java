package io.basc.start.user.security;

import java.util.HashMap;
import java.util.Map;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.security.session.UserSession;
import io.basc.start.user.pojo.User;

@Provider
public class DefaultUserLoginService implements UserLoginService {

	public Map<String, Object> login(User user, HttpChannel httpChannel) {
		Map<String, Object> map = new HashMap<String, Object>(8);
		UserSession<Long> userSession = httpChannel.createUserSession(user.getUid());
		map.put("token", userSession.getId());
		map.put("uid", userSession.getUid());
		map.putAll(info(user));
		return map;
	}

	public Map<String, Object> info(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		return map;
	}
}
