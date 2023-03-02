package io.basc.start.sms;

import java.util.Arrays;
import java.util.List;

public interface Sms {
	default SendSmsResponse send(SendSmsRequest request) {
		return send(Arrays.asList(request)).get(0);
	}

	List<SendSmsResponse> send(List<SendSmsRequest> requests);
}
