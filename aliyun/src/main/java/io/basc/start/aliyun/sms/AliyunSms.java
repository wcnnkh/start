package io.basc.start.aliyun.sms;

import java.util.Collection;
import java.util.Map;

import io.basc.framework.util.Status;

public interface AliyunSms {
	Status<String> send(MessageModel messageModel, Map<String, String> parameterMap, Collection<String> phones);
}
