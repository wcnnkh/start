package io.basc.integration.alibaba.dayu;

import io.basc.framework.context.result.Result;

public interface AliDaYu {
	Result sendMessage(MessageModel messageModel, String sms_param, String toPhones);
}
