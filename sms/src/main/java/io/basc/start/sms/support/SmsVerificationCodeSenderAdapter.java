package io.basc.start.sms.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.core.Ordered;
import io.basc.framework.util.CollectionUtils;
import io.basc.framework.util.StringUtils;
import io.basc.start.sms.MessageTemplate;
import io.basc.start.sms.Sms;
import io.basc.start.sms.SmsRequest;
import io.basc.start.sms.SmsResponse;
import io.basc.start.verificationcode.VerificationCodeRecipient;
import io.basc.start.verificationcode.VerificationCodeRequest;
import io.basc.start.verificationcode.VerificationCodeResponse;
import io.basc.start.verificationcode.VerificationCodeSenderAdapter;

@Provider(order = Ordered.LOWEST_PRECEDENCE)
public class SmsVerificationCodeSenderAdapter implements VerificationCodeSenderAdapter {
	private final Map<String, MessageTemplate> templateMap = new HashMap<>();
	private final Sms sms;
	private String product;
	private String codeKey = "code";
	private String productKey = "product";

	public SmsVerificationCodeSenderAdapter(Sms sms) {
		this.sms = sms;
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

	public Map<String, MessageTemplate> getTemplateMap() {
		return templateMap;
	}

	public Sms getSms() {
		return sms;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@Override
	public boolean canSend(VerificationCodeRecipient recipient) {
		return templateMap.containsKey(recipient.getType());
	}

	protected SmsRequest wrap(VerificationCodeRequest request) {
		MessageTemplate messageTemplate = templateMap.get(request.getRecipient().getType());
		if (messageTemplate == null) {
			return null;
		}

		Map<String, String> params = new HashMap<String, String>(4);
		params.put(codeKey, request.getCode());
		if (StringUtils.isNotEmpty(product)) {
			params.put(productKey, product);
		}
		return SmsRequest.builder().template(messageTemplate).phone(request.getRecipient().getUser())
				.templateParams(params).build();
	}

	@Override
	public List<VerificationCodeResponse> send(List<VerificationCodeRequest> requests) {
		if (CollectionUtils.isEmpty(requests)) {
			return Collections.emptyList();
		}

		List<VerificationCodeResponse> responses = new ArrayList<>(requests.size());
		List<SmsRequest> smsRequests = requests.stream().map((r) -> {
			SmsRequest request = wrap(r);
			if (request == null) {
				responses.add(VerificationCodeResponse.builder().request(r).success(false).message("not supported")
						.build());
				return null;
			}
			return request;
		}).collect(Collectors.toList());

		List<SmsResponse> smsResponses = sms.send(smsRequests);

		return responses;
	}
}
