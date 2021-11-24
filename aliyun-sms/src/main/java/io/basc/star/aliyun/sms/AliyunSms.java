package io.basc.star.aliyun.sms;

import java.util.Map;

import io.basc.framework.util.Status;

public interface AliyunSms {
	Status<String> send(AliSmsModel messageModel, Map<String, ?> parameterMap, String phone);
}
