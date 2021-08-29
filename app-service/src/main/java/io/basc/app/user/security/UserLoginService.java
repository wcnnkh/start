package io.basc.app.user.security;

import io.basc.app.user.pojo.User;
import io.basc.framework.mvc.HttpChannel;

import java.util.Map;

public interface UserLoginService {
	Map<String, Object> login(User user, HttpChannel httpChannel);
	
	Map<String, Object> info(User user);
}
