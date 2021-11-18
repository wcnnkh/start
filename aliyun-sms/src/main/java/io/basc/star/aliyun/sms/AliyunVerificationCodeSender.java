package io.basc.star.aliyun.sms;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.core.Ordered;
import io.basc.framework.util.Status;
import io.basc.start.verificationcode.Receiver;
import io.basc.start.verificationcode.VerificationCodeSender;
import io.basc.start.verificationcode.support.PhoneReceiver;

@Provider(order = Ordered.LOWEST_PRECEDENCE)
public class AliyunVerificationCodeSender implements VerificationCodeSender {
	private final Map<String, MessageModel> messageModelMap = new HashMap<>();
	private final AliyunSms aliyunSms;
	/**
	 * 是否接受全部接收人
	 */
	private boolean acceptAll = false;

	private final String product;
	private String productKey = "product";

	public AliyunVerificationCodeSender(AliyunSms aliyunSms, String product) {
		this.aliyunSms = aliyunSms;
		this.product = product;
	}

	public boolean isAcceptAll() {
		return acceptAll;
	}

	public void setAcceptAll(boolean acceptAll) {
		this.acceptAll = acceptAll;
	}

	public AliyunSms getAliyunSms() {
		return aliyunSms;
	}

	public String getProductKey() {
		return productKey;
	}

	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}

	public String getProduct() {
		return product;
	}

	@Override
	public boolean canSend(Receiver receiver) {
		if (!messageModelMap.containsKey(receiver.getTag())) {
			return false;
		}

		if (acceptAll) {
			return true;
		}

		return receiver instanceof PhoneReceiver;
	}

	public MessageModel getMessageModel(Receiver receiver) {
		return messageModelMap.get(receiver.getTag());
	}

	public void registerMessageModel(String tag, MessageModel messageModel) {
		messageModelMap.put(tag, messageModel);
	}

	@Override
	public Status<String> send(String code, Receiver receiver) {
		MessageModel messageModel = getMessageModel(receiver);
		if (messageModel == null) {
			throw new IllegalArgumentException(receiver.toString());
		}

		Map<String, String> params = new HashMap<String, String>();
		params.put("code", code);
		params.put("product", getProduct());
		return getAliyunSms().send(messageModel, params,
				Arrays.asList(receiver.getReceiver()));
	}
}
