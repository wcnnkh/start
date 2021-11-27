package io.basc.start.sms.support;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.core.Ordered;
import io.basc.framework.logger.Logger;
import io.basc.framework.logger.LoggerFactory;
import io.basc.framework.util.CollectionUtils;
import io.basc.framework.util.Pair;
import io.basc.framework.util.StringUtils;
import io.basc.start.sms.MessageTemplate;
import io.basc.start.sms.Sms;
import io.basc.start.sms.SendSmsRequest;
import io.basc.start.sms.SendSmsResponse;
import io.basc.start.verificationcode.VerificationCodeRecipient;
import io.basc.start.verificationcode.VerificationCodeRequest;
import io.basc.start.verificationcode.VerificationCodeResponse;
import io.basc.start.verificationcode.VerificationCodeSenderAdapter;

@Provider(order = Ordered.LOWEST_PRECEDENCE)
public class SmsVerificationCodeSenderAdapter implements VerificationCodeSenderAdapter {
	private static Logger logger = LoggerFactory.getLogger(SmsVerificationCodeSenderAdapter.class);
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

	protected SendSmsRequest wrap(VerificationCodeRequest request) {
		MessageTemplate messageTemplate = templateMap.get(request.getRecipient().getType());
		if (messageTemplate == null) {
			return null;
		}

		Map<String, String> params = new HashMap<String, String>(4);
		params.put(codeKey, request.getCode());
		if (StringUtils.isNotEmpty(product)) {
			params.put(productKey, product);
		}
		return SendSmsRequest.builder().template(messageTemplate).phone(request.getRecipient().getUser())
				.templateParams(params).build();
	}

	@Override
	public List<VerificationCodeResponse> send(List<VerificationCodeRequest> requests) {
		if (CollectionUtils.isEmpty(requests)) {
			return Collections.emptyList();
		}
		
		VerificationCodeRequest[] arrays = requests.toArray(new VerificationCodeRequest[0]);
		List<Pair<Integer, SendSmsRequest>> list = new ArrayList<Pair<Integer,SendSmsRequest>>();
		for(int i=0; i<arrays.length; i++) {
			SendSmsRequest smsRequest = wrap(arrays[i]);
			if(smsRequest == null) {
				continue;
			}
			
			list.add(new Pair<Integer, SendSmsRequest>(i, smsRequest));
		}
		
		VerificationCodeResponse[] responses = new VerificationCodeResponse[arrays.length];
		List<SendSmsResponse> smsResponses = sms.send(list.stream().map((e) -> e.getValue()).collect(Collectors.toList()));
		if(smsResponses.size() != list.size()) {
			logger.error("The number of requests[{}] and responses[{}] is inconsistent", list, smsResponses);
		}
		
		Iterator<SendSmsResponse> responseIterator = smsResponses.iterator();
		Iterator<Pair<Integer, SendSmsRequest>> requestIterator = list.iterator();
		while(responseIterator.hasNext() && requestIterator.hasNext()) {
			SendSmsResponse response = responseIterator.next();
			Pair<Integer, SendSmsRequest> request = requestIterator.next();
			VerificationCodeRequest verificationCodeRequest = arrays[request.getKey()];
			responses[request.getKey()] = VerificationCodeResponse.builder().request(verificationCodeRequest).success(response.isSuccess()).message(response.getMessage()).build();
		}
		return Arrays.asList(responses);
	}
	
}
