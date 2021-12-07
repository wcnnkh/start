package io.basc.start.verificationcode.support;

import io.basc.framework.data.TemporaryStorage;
import io.basc.start.verificationcode.VerificationCodeRecipient;

public class DefaultVerificationCodeStorage implements VerificationCodeStorage {
	private final TemporaryStorage temporaryStorage;

	public DefaultVerificationCodeStorage(TemporaryStorage temporaryStorage) {
		this.temporaryStorage = temporaryStorage;
	}

	protected String getKey(VerificationCodeRecipient recipient) {
		return getClass().getSimpleName() + "." + recipient.getUser()
				+ (recipient.getType() == null ? "" : ("." + recipient.getType()));
	}

	@Override
	public void set(VerificationCodeRecipient recipient, VerificationCode verificationCode, long expirationTime) {
		temporaryStorage.set(getKey(recipient), expirationTime, verificationCode);
	}

	@Override
	public void delete(VerificationCodeRecipient recipient) {
		temporaryStorage.delete(getKey(recipient));
	}

	@Override
	public VerificationCode getVerificationCode(VerificationCodeRecipient recipient) {
		return temporaryStorage.get(getKey(recipient));
	}

}
