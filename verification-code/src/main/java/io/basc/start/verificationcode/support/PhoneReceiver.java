package io.basc.start.verificationcode.support;

public class PhoneReceiver extends SimpleReceiver {
	private static final long serialVersionUID = 1L;

	public PhoneReceiver() {
	}

	public PhoneReceiver(String phone) {
		super(phone);
	}
	
	public PhoneReceiver(String phone, String tag) {
		super(phone, tag);
	}
}
