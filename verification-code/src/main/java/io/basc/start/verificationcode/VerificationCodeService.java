package io.basc.start.verificationcode;

import io.basc.framework.util.Status;

public interface VerificationCodeService {
	/**
	 * 发送一个随机的验证码
	 * 
	 * @param receiver
	 * @return 如果发送成功将返回验证码
	 */
	Status<String> send(Receiver receiver);

	/**
	 * 发送一个验证码
	 * 
	 * @param code
	 * @param receiver
	 * @return
	 */
	Status<String> send(String code, Receiver receiver);

	/**
	 * 验证验证码
	 * 
	 * @param code
	 * @param receiver
	 * @return
	 */
	Status<String> validate(String code, Receiver receiver);
}
