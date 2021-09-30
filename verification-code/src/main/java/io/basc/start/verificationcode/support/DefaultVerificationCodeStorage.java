package io.basc.start.verificationcode.support;

import io.basc.framework.data.TemporaryStorage;
import io.basc.start.verificationcode.Receiver;

public class DefaultVerificationCodeStorage implements VerificationCodeStorage {
	private final TemporaryStorage temporaryStorage;

	public DefaultVerificationCodeStorage(TemporaryStorage temporaryStorage) {
		this.temporaryStorage = temporaryStorage;
	}

	protected String getKey(Receiver receiver) {
		return getClass().getSimpleName() + "." + receiver.getReceiver()
				+ (receiver.getTag() == null ? "" : ("." + receiver.getTag()));
	}

	@Override
	public void set(Receiver receiver, VerificationCode verificationCode, int expirationTime) {
		temporaryStorage.set(getKey(receiver), expirationTime, verificationCode);
	}

	@Override
	public void delete(Receiver receiver) {
		temporaryStorage.delete(getKey(receiver));
	}

	@Override
	public VerificationCode getVerificationCode(Receiver receiver) {
		return temporaryStorage.get(getKey(receiver));
	}

}
