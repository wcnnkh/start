package io.basc.start.verificationcode;

import java.util.Arrays;
import java.util.List;

public interface VerificationCodeSender {
	/**
	 * 发送验证码
	 * 
	 * @param receiver
	 * @return
	 */
	default VerificationCodeResponse send(VerificationCodeRequest request) {
		return send(Arrays.asList(request)).get(0);
	}

	/**
	 * 批量发送验证码
	 * 
	 * @param requests
	 * @return 返回和请求的顺序一致
	 */
	List<VerificationCodeResponse> send(List<VerificationCodeRequest> requests);
}
