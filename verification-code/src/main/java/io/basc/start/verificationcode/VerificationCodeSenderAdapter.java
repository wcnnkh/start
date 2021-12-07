package io.basc.start.verificationcode;

public interface VerificationCodeSenderAdapter extends VerificationCodeSender {
	boolean canSend(VerificationCodeRecipient recipient);
}
