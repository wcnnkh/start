package io.basc.start.verificationcode.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.core.Ordered;
import io.basc.framework.data.memory.MemoryDataOperations;
import io.basc.framework.factory.ConfigurableServices;
import io.basc.framework.logger.Logger;
import io.basc.framework.logger.LoggerFactory;
import io.basc.framework.util.CollectionUtils;
import io.basc.framework.util.RandomUtils;
import io.basc.framework.util.Status;
import io.basc.framework.util.TimeUtils;
import io.basc.start.verificationcode.VerificationCodeRecipient;
import io.basc.start.verificationcode.VerificationCodeRequest;
import io.basc.start.verificationcode.VerificationCodeResponse;
import io.basc.start.verificationcode.VerificationCodeSender;
import io.basc.start.verificationcode.VerificationCodeSenderAdapter;
import io.basc.start.verificationcode.VerificationCodeService;

@Provider(order = Ordered.LOWEST_PRECEDENCE, value = VerificationCodeService.class)
public class DefaultVerificationCodeService extends ConfigurableServices<VerificationCodeSenderAdapter>
		implements VerificationCodeService {
	private static Logger logger = LoggerFactory.getLogger(DefaultVerificationCodeService.class);

	private VerificationCodeConfiguration configuration;
	private final VerificationCodeStorage strategy;

	public DefaultVerificationCodeService() {
		this(new DefaultVerificationCodeStorage(new MemoryDataOperations()));
	}

	public DefaultVerificationCodeService(VerificationCodeStorage strategy) {
		super(VerificationCodeSenderAdapter.class);
		this.strategy = strategy;
	}

	public VerificationCodeConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(VerificationCodeConfiguration configuration) {
		this.configuration = configuration;
	}

	public VerificationCodeStorage getStrategy() {
		return strategy;
	}

	@Override
	public String[] getRandomCodes(int size) {
		String[] codes = new String[size];
		for (int i = 0; i < codes.length; i++) {
			codes[i] = RandomUtils.getNumCode(getConfiguration().getCodeLength());
		}
		return codes;
	}

	@Override
	public List<VerificationCodeResponse> send(List<VerificationCodeRequest> requests) {
		if (CollectionUtils.isEmpty(requests)) {
			return Collections.emptyList();
		}

		if (getConfiguration().isTest()) {
			logger.info("测试模式发送验证码请求：{}", requests);
			return requests.stream().map((r) -> VerificationCodeResponse.builder().request(r).success(true).build())
					.collect(Collectors.toList());
		}

		VerificationCodeRequest[] arrays = requests.toArray(new VerificationCodeRequest[0]);
		VerificationCodeResponse[] responses = new VerificationCodeResponse[arrays.length];
		Map<VerificationCodeRecipient, VerificationCode> verificationCodeMap = new HashMap<>();
		for (VerificationCodeSenderAdapter adapter : this) {
			LinkedHashMap<Integer, VerificationCodeRequest> requestMap = new LinkedHashMap<>();
			for (int i = 0; i < arrays.length; i++) {
				VerificationCodeRequest request = arrays[i];
				if(request == null) {
					continue;
				}
				
				VerificationCode verificationCode = verificationCodeMap.get(request.getRecipient());
				if (verificationCode == null) {
					verificationCode = strategy.getVerificationCode(request.getRecipient());
					if (verificationCode == null) {
						verificationCode = new VerificationCode();
					}
					verificationCodeMap.put(request.getRecipient(), verificationCode);
				}

				Status<String> status = getConfiguration().check(verificationCode);
				if (!status.isActive()) {
					responses[i] = VerificationCodeResponse.builder().request(request).success(false)
							.message(status.get()).build();
					arrays[i] = null;
					continue;
				}
				
				if(adapter.canSend(request.getRecipient())) {
					requestMap.put(i, request);
					arrays[i] = null;
					continue;
				}
			}
			
			if(!requestMap.isEmpty()) {
				List<VerificationCodeResponse> sendResponses = send(verificationCodeMap, requestMap.entrySet().stream().map((e) -> e.getValue()).collect(Collectors.toList()), adapter);
				Iterator<Entry<Integer, VerificationCodeRequest>> entryIterator = requestMap.entrySet().iterator();
				Iterator<VerificationCodeResponse> sendIterator = sendResponses.iterator();
				while(entryIterator.hasNext() && sendIterator.hasNext()) {
					Entry<Integer, VerificationCodeRequest> entry = entryIterator.next();
				}
			}

			while (iterator.hasNext()) {
				VerificationCodeRequest codeRequest = iterator.next();

				VerificationCode verificationCode = verificationCodeMap.get(codeRequest.getRecipient());
				if (verificationCode == null) {
					verificationCode = strategy.getVerificationCode(codeRequest.getRecipient());
					if (verificationCode == null) {
						verificationCode = new VerificationCode();
					}
					verificationCodeMap.put(codeRequest.getRecipient(), verificationCode);
				}

				Status<String> status = getConfiguration().check(verificationCode);
				if (!status.isActive()) {
					responses.add(VerificationCodeResponse.builder().request(codeRequest).success(false)
							.message(status.get()).build());
					iterator.remove();
					continue;
				}

				if (adapter.canSend(codeRequest.getRecipient())) {
					codeRequests.add(codeRequest);
					iterator.remove();
					continue;
				}
			}

			if (!codeRequests.isEmpty()) {
				List<VerificationCodeResponse> sendResponses = send(verificationCodeMap, codeRequests, adapter);
				responses.addAll(sendResponses);
			}
		}

		if (!list.isEmpty()) {
			logger.error("Not support send verification code requests: ", list);
			for (VerificationCodeRequest request : list) {
				responses.add(VerificationCodeResponse.builder().request(request).success(false).message("not support")
						.build());
			}
		}
		return responses;
	}

	private List<VerificationCodeResponse> send(Map<VerificationCodeRecipient, VerificationCode> verificationCodeMap,
			List<VerificationCodeRequest> requests, VerificationCodeSender sender) {
		List<VerificationCodeResponse> sendResponses = sender.send(requests);
		if (sendResponses.size() != requests.size()) {
			logger.error("The number of requests[{}] and responses[{}] is inconsistent", requests, sendResponses);
		}

		Iterator<VerificationCodeResponse> responseIterator = sendResponses.iterator();
		while (responseIterator.hasNext()) {
			VerificationCodeResponse response = responseIterator.next();
			if (response.isSuccess()) {
				VerificationCode verificationCode = verificationCodeMap.get(response.getRequest().getRecipient());
				// 不可能为空
				if (System.currentTimeMillis() - verificationCode.getLastSendTime() > TimeUtils.ONE_DAY) {
					verificationCode.setCount(1);
				} else {
					verificationCode.setCount(verificationCode.getCount() + 1);
				}

				verificationCode.setCode(response.getRequest().getCode());
				verificationCode.setLastSendTime(System.currentTimeMillis());
				strategy.set(response.getRequest().getRecipient(), verificationCode, TimeUtils.ONE_DAY / 1000);
			}
		}
		return sendResponses;
	}

	@Override
	public List<VerificationCodeResponse> validate(List<VerificationCodeRequest> requests) {
		if (CollectionUtils.isEmpty(requests)) {
			return Collections.emptyList();
		}

		return requests.stream().map((request) -> {
			VerificationCode info = getStrategy().getVerificationCode(request.getRecipient());
			Status<String> status = getConfiguration().check(request.getCode(), info);
			if (status.isActive() && getConfiguration().isDeleteAfterCheck()) {
				getStrategy().delete(request.getRecipient());
			}
			return VerificationCodeResponse.builder().request(request).success(status.isActive())
					.message(status.isActive() ? null : status.get()).build();
		}).collect(Collectors.toList());
	}
}
