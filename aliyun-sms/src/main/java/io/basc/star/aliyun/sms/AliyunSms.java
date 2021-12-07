package io.basc.star.aliyun.sms;

import io.basc.framework.util.CollectionUtils;
import io.basc.start.sms.Sms;
import io.basc.start.sms.SendSmsRequest;
import io.basc.start.sms.SendSmsResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public interface AliyunSms extends Sms {
	default List<SendSmsResponse> send(List<SendSmsRequest> requests) {
		if (CollectionUtils.isEmpty(requests)) {
			return Collections.emptyList();
		}

		List<AliyunSendSmsResponse> aliyunSmsResponses = batchSend(requests
				.stream()
				.map((r) -> AliyunSendSmsRequest.builder().send(r).build())
				.collect(Collectors.toList()));
		List<SendSmsResponse> responses = new ArrayList<SendSmsResponse>();
		Iterator<SendSmsRequest> requestIterator = requests.iterator();
		Iterator<AliyunSendSmsResponse> responseIterator = aliyunSmsResponses
				.iterator();
		while (requestIterator.hasNext() && responseIterator.hasNext()) {
			AliyunSendSmsResponse response = responseIterator.next();
			SendSmsRequest request = requestIterator.next();
			responses.add(SendSmsResponse.builder().request(request)
					.success(response.isSuccess())
					.message(response.getMessage()).build());
		}
		return responses;
	}

	default AliyunSendSmsResponse send(AliyunSendSmsRequest request) {
		return batchSend(Arrays.asList(request)).get(0);
	}

	/**
	 * 批量发送
	 * 
	 * @param requests
	 * @return 返回和请求的顺序一致
	 */
	List<AliyunSendSmsResponse> batchSend(List<AliyunSendSmsRequest> requests);
}
