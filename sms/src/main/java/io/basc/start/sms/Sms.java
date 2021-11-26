package io.basc.start.sms;

import java.util.Arrays;
import java.util.List;

public interface Sms {
	default SmsResponse send(SmsRequest request) {
		return send(Arrays.asList(request)).get(0);
	}

	/**
	 * 批量发送
	 * 
	 * @param requests
	 * @return
	 */
	List<SmsResponse> send(List<SmsRequest> requests);
}
