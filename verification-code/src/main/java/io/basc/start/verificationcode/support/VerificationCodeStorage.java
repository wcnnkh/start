package io.basc.start.verificationcode.support;

import java.util.concurrent.TimeUnit;

import io.basc.start.verificationcode.VerificationCodeRecipient;

public interface VerificationCodeStorage {
	void set(VerificationCodeRecipient recipient, VerificationCode verificationCode, long expirationTime,
			TimeUnit timeUnit);

	void delete(VerificationCodeRecipient recipient);

	VerificationCode getVerificationCode(VerificationCodeRecipient recipient);
}
