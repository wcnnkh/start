package io.basc.start.verificationcode.support;

import io.basc.start.verificationcode.Receiver;

public interface VerificationCodeStorage {
	/**
	 * @param receiver
	 * @param verificationCode
	 * @param expirationTime   单位：秒
	 */
	void set(Receiver receiver, VerificationCode verificationCode, int expirationTime);

	void delete(Receiver receiver);

	VerificationCode getVerificationCode(Receiver receiver);
}
