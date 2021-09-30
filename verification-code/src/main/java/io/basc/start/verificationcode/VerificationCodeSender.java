package io.basc.start.verificationcode;

import io.basc.framework.util.Status;

public interface VerificationCodeSender {
	boolean canSend(Receiver receiver);

	Status<String> send(String code, Receiver receiver);
}
