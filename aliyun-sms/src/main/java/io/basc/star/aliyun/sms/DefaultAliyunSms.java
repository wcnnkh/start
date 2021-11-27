package io.basc.star.aliyun.sms;

import io.basc.framework.json.JSONUtils;
import io.basc.framework.lang.NestedExceptionUtils;
import io.basc.framework.logger.Logger;
import io.basc.framework.logger.LoggerFactory;
import io.basc.framework.util.CollectionUtils;
import io.basc.framework.util.Pair;
import io.basc.framework.validation.FastValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendBatchSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendBatchSmsResponse;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;

public class DefaultAliyunSms implements AliyunSms {
	private static Logger logger = LoggerFactory
			.getLogger(DefaultAliyunSms.class);
	private final Client client;

	public DefaultAliyunSms(String accessKeyId, String accessKeySecret)
			throws Exception {
		Config config = new Config();
		config.setAccessKeyId(accessKeyId);
		config.setAccessKeySecret(accessKeySecret);
		this.client = new Client(config);
	}

	public DefaultAliyunSms(Client client) {
		this.client = client;
	}

	@Override
	public AliyunSendSmsResponse send(AliyunSendSmsRequest request) {
		FastValidator.validate(request);
		SendSmsRequest sendSmsRequest = new SendSmsRequest();
		sendSmsRequest.setOutId(request.getQutId());
		sendSmsRequest.setSmsUpExtendCode(request.getUpExtendCode());
		sendSmsRequest.setSignName(request.getSend().getTemplate().getSignName());
		sendSmsRequest.setPhoneNumbers(request.getSend().getPhone());
		sendSmsRequest.setTemplateCode(request.getSend().getTemplate()
				.getCode());
		if (request.getSend().getTemplateParams() != null) {
			sendSmsRequest.setTemplateParam(JSONUtils.getJsonSupport()
					.toJSONString(request.getSend().getTemplateParams()));
		}

		try {
			SendSmsResponse response = client.sendSms(sendSmsRequest);
			return AliyunSendSmsResponse
					.builder()
					.request(request)
					.code(response.getBody().getCode())
					.bizId(response.getBody().getBizId())
					.message(response.getBody().getMessage())
					.requestId(response.getBody().getRequestId())
					.success(
							response.getBody().getCode() != null
									&& response.getBody().getCode()
											.equals("OK")).build();
		} catch (Exception e) {
			logger.error(e, "Send sms request: {}", request);
			return AliyunSendSmsResponse.builder().request(request)
					.success(false)
					.message(NestedExceptionUtils.getNonEmptyMessage(e, false))
					.build();
		}
	}

	@Override
	public List<AliyunSendSmsResponse> batchSend(
			List<AliyunSendSmsRequest> requests) {
		if (CollectionUtils.isEmpty(requests)) {
			return Collections.emptyList();
		}

		if (requests.size() == 1) {
			return Arrays.asList(send(requests.get(0)));
		}

		for (AliyunSendSmsRequest request : requests) {
			FastValidator.validate(request);
		}

		AliyunSendSmsResponse[] responses = new AliyunSendSmsResponse[requests
				.size()];
		AliyunSendSmsRequest[] arrays = requests
				.toArray(new AliyunSendSmsRequest[0]);
		Map<String, List<Pair<Integer, AliyunSendSmsRequest>>> map = new HashMap<String, List<Pair<Integer, AliyunSendSmsRequest>>>();
		for (int i = 0; i < arrays.length; i++) {
			AliyunSendSmsRequest request = arrays[i];
			List<Pair<Integer, AliyunSendSmsRequest>> list = map.get(request
					.getSend().getTemplate().getCode());
			if (list == null) {
				list = new ArrayList<Pair<Integer, AliyunSendSmsRequest>>();
			}
			list.add(new Pair<Integer, AliyunSendSmsRequest>(i, request));
			map.put(request.getSend().getTemplate().getCode(), list);
		}

		for (Entry<String, List<Pair<Integer, AliyunSendSmsRequest>>> entry : map
				.entrySet()) {
			List<Pair<Integer, AliyunSendSmsRequest>> list = entry.getValue();
			List<String> phoneNumberJson = new ArrayList<String>();
			List<String> signNameJson = new ArrayList<String>();
			List<Object> templateParamJson = new ArrayList<Object>();
			List<String> smsUpExtendCodeJson = new ArrayList<String>();
			for (Pair<Integer, AliyunSendSmsRequest> pair : list) {
				AliyunSendSmsRequest request = pair.getValue();
				phoneNumberJson.add(request.getSend().getPhone());
				signNameJson.add(request.getSend().getTemplate().getSignName());
				templateParamJson.add(request.getSend().getTemplateParams());
				smsUpExtendCodeJson.add(request.getUpExtendCode());
			}

			SendBatchSmsRequest sendBatchSmsRequest = new SendBatchSmsRequest();
			sendBatchSmsRequest.setPhoneNumberJson(JSONUtils.getJsonSupport()
					.toJSONString(phoneNumberJson));
			sendBatchSmsRequest.setSignNameJson(JSONUtils.getJsonSupport()
					.toJSONString(signNameJson));
			sendBatchSmsRequest.setSmsUpExtendCodeJson(JSONUtils
					.getJsonSupport().toJSONString(smsUpExtendCodeJson));
			sendBatchSmsRequest.setTemplateCode(entry.getKey());
			sendBatchSmsRequest.setTemplateParamJson(JSONUtils.getJsonSupport()
					.toJSONString(templateParamJson));

			try {
				SendBatchSmsResponse response = client
						.sendBatchSms(sendBatchSmsRequest);
				boolean success = response.getBody().getCode() != null
						&& response.getBody().getCode().equals("OK");
				for (Pair<Integer, AliyunSendSmsRequest> pair : list) {
					responses[pair.getKey()] = AliyunSendSmsResponse.builder()
							.request(pair.getValue())
							.bizId(response.getBody().getBizId())
							.code(response.getBody().getCode())
							.message(response.getBody().getMessage())
							.requestId(response.getBody().getRequestId())
							.success(success).build();
				}
			} catch (Exception e) {
				logger.error(e, "Send requests: {}", list);
				for (Pair<Integer, AliyunSendSmsRequest> pair : list) {
					responses[pair.getKey()] = AliyunSendSmsResponse
							.builder()
							.request(pair.getValue())
							.success(false)
							.message(
									NestedExceptionUtils.getNonEmptyMessage(e,
											false)).build();
				}
			}
		}
		return Arrays.asList(responses);
	}
}
