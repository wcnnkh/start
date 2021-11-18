package io.basc.star.aliyun.sms;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

import io.basc.framework.util.Status;

public interface AliyunSms {
	Status<String> send(MessageModel messageModel, Map<String, ?> parameterMap, Collection<String> phones);
	
	default Status<String> send(MessageModel messageModel, Map<String, ?> parameterMap, String phone){
		return send(messageModel, parameterMap, Arrays.asList(phone));
	}
}
