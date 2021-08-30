package io.basc.start.alibaba.dayu;

import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.core.utils.XTime;
import io.basc.framework.redis.core.Redis;

import java.lang.reflect.InvocationTargetException;

public final class RedisXmlPhoneVerificationCode extends AbstractXmlPhoneVerificationCode {
	private final Redis redis;
	private final long tempSuffix = System.currentTimeMillis();

	public RedisXmlPhoneVerificationCode(String xmlPath, Redis redis, ResultFactory resultFactory)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		super(xmlPath, resultFactory);
		this.redis = redis;
	}

	@Override
	public PhoneVerificationCode getCacheData(int configIndex, String phone) {
		StringBuilder sb = new StringBuilder();
		sb.append(phone);
		sb.append("&").append(configIndex);
		sb.append("&").append(tempSuffix);
		return (PhoneVerificationCode) redis.getObjectCommands().get(sb.toString());
	}

	@Override
	public void setCacheData(int configIndex, String phone, PhoneVerificationCode json) {
		if (json == null) {
			return;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(phone);
		sb.append("&").append(configIndex);
		sb.append("&").append(tempSuffix);
		redis.getObjectCommands().setex(sb.toString(), (int) (XTime.ONE_DAY / 1000), json);
	}
}
