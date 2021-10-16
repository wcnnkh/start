package io.basc.start.app.user.security;

import io.basc.framework.mvc.HttpChannel;
import io.basc.start.app.user.pojo.User;

import java.util.Map;

public interface UserLoginService {
	Map<String, Object> login(User user, HttpChannel httpChannel);
	
	Map<String, Object> info(User user);
}
