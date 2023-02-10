package io.basc.start.usercenter.security;

import java.util.Map;

import io.basc.framework.mvc.HttpChannel;
import io.basc.start.usercenter.pojo.User;

public interface UserLoginService {
	Map<String, Object> login(User user, HttpChannel httpChannel);
	
	Map<String, Object> info(User user);
}
