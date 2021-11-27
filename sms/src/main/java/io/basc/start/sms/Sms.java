package io.basc.start.sms;

import java.util.Arrays;
import java.util.List;

public interface Sms {
	default SendSmsResponse send(SendSmsRequest request) {
		return send(Arrays.asList(request)).get(0);
	}

	/**
	 * 批量发送
	 * 
	 * @param requests
	 * @return 返回和请求的顺序一致
	 */
	List<SendSmsResponse> send(List<SendSmsRequest> requests);
}
