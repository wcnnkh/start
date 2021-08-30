package io.basc.start.vc.service.impl;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import io.basc.framework.context.result.Result;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.data.TemporaryStorage;
import io.basc.framework.util.StringUtils;
import io.basc.start.alibaba.dayu.MessageModel;
import io.basc.start.alibaba.dayu.Sender;
import io.basc.start.vc.enums.VerificationCodeType;
import io.basc.start.vc.service.AbstractVerificationCodeService;
import io.basc.start.vc.service.PhoneVerificationCodeService;

public class AliDaYuPhoneVerificationCodeService extends AbstractVerificationCodeService
		implements PhoneVerificationCodeService {
	private EnumMap<VerificationCodeType, MessageModel> messageModelMap = new EnumMap<VerificationCodeType, MessageModel>(
			VerificationCodeType.class);
	private Sender sender;
	private String product;

	public AliDaYuPhoneVerificationCodeService(TemporaryStorage temporaryCache, Sender sender, String product,
			ResultFactory resultFactory) {
		super(temporaryCache, resultFactory);
		this.sender = sender;
		this.product = product;
	}

	public EnumMap<VerificationCodeType, MessageModel> getMessageModelMap() {
		return messageModelMap;
	}

	@Override
	protected Result sendInternal(String user, String code, VerificationCodeType type) {
		if (StringUtils.isEmpty(user, code)) {
			throw new IllegalArgumentException("参数错误");
		}

		MessageModel messageModel = getMessageModelMap().get(type);
		if (messageModel == null) {
			throw new IllegalArgumentException("不支持的验证码类型(" + type + ")");
		}

		Map<String, String> parameterMap = new HashMap<String, String>();
		parameterMap.put("code", code);
		parameterMap.put("product", product);
		return sender.send(messageModel, parameterMap, user);
	}
}
