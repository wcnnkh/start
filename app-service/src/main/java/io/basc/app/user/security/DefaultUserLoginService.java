package io.basc.app.user.security;

import io.basc.app.user.pojo.User;
import io.basc.framework.context.annotation.Provider;
import io.basc.framework.http.HttpCookie;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.mvc.security.UserSessionResolver;
import io.basc.framework.security.session.UserSession;
import io.basc.framework.util.XUtils;

import java.util.HashMap;
import java.util.Map;

@Provider
public class DefaultUserLoginService implements UserLoginService {

	public Map<String, Object> login(User user, HttpChannel httpChannel) {
		Map<String, Object> map = new HashMap<String, Object>(8);
		UserSession<Long> userSession = httpChannel.createUserSession(Long.class, user.getUid(), XUtils.getUUID());
		map.put("token", userSession.getId());
		map.put("uid", userSession.getUid());
		map.putAll(info(user));
		HttpCookie uidCookie = new HttpCookie(UserSessionResolver.UID_NAME, userSession.getUid() + "");
		uidCookie.setPath("/");
		HttpCookie tokenCookie = new HttpCookie(UserSessionResolver.TOKEN_NAME, userSession.getId() + "");
		tokenCookie.setPath("/");
		httpChannel.getResponse().addCookie(uidCookie);
		httpChannel.getResponse().addCookie(tokenCookie);
		return map;
	}

	public Map<String, Object> info(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		return map;
	}
}
