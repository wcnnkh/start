package scw.integration.alibaba.dayu;

import scw.context.result.Result;

public interface AliDaYu {
	Result sendMessage(MessageModel messageModel, String sms_param, String toPhones);
}
