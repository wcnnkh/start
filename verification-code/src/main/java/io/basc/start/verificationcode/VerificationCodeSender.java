package io.basc.start.verificationcode;

import java.util.Arrays;
import java.util.List;

public interface VerificationCodeSender {
	default VerificationCodeResponse send(VerificationCodeRequest request) {
		return send(Arrays.asList(request)).get(0);
	}

	List<VerificationCodeResponse> send(List<VerificationCodeRequest> requests);
}
