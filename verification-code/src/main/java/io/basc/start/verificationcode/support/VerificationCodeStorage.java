package io.basc.start.verificationcode.support;

import io.basc.start.verificationcode.VerificationCodeRecipient;

public interface VerificationCodeStorage {
	/**
	 * @param recipient
	 * @param verificationCode
	 * @param expirationTime   单位：秒
	 */
	void set(VerificationCodeRecipient recipient, VerificationCode verificationCode, long expirationTime);

	void delete(VerificationCodeRecipient recipient);

	VerificationCode getVerificationCode(VerificationCodeRecipient recipient);
}
