package io.basc.start.verificationcode;

import javax.validation.constraints.Null;

public interface Receiver {
	String getReceiver();

	@Null
	String getTag();
}
