package io.basc.start.verificationcode.support;

import java.util.concurrent.TimeUnit;

import io.basc.framework.data.TemporaryDataOperations;
import io.basc.start.verificationcode.VerificationCodeRecipient;

public class DefaultVerificationCodeStorage implements VerificationCodeStorage {
	private final TemporaryDataOperations storageOperations;

	public DefaultVerificationCodeStorage(TemporaryDataOperations storageOperations) {
		this.storageOperations = storageOperations;
	}

	protected String getKey(VerificationCodeRecipient recipient) {
		return getClass().getSimpleName() + "." + recipient.getUser()
				+ (recipient.getType() == null ? "" : ("." + recipient.getType()));
	}

	@Override
	public void set(VerificationCodeRecipient recipient, VerificationCode verificationCode, long expirationTime,
			TimeUnit timeUnit) {
		storageOperations.set(getKey(recipient), verificationCode, expirationTime, timeUnit);
	}

	@Override
	public void delete(VerificationCodeRecipient recipient) {
		storageOperations.delete(getKey(recipient));
	}

	@Override
	public VerificationCode getVerificationCode(VerificationCodeRecipient recipient) {
		return storageOperations.get(VerificationCode.class, getKey(recipient));
	}

}
