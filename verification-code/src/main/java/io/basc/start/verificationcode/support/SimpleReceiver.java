package io.basc.start.verificationcode.support;

import java.io.Serializable;

import io.basc.start.verificationcode.Receiver;

public class SimpleReceiver implements Receiver, Serializable {
	private static final long serialVersionUID = 1L;
	private String receiver;
	private String tag;

	public SimpleReceiver() {
	}

	public SimpleReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public SimpleReceiver(String receiver, String tag) {
		this.receiver = receiver;
		this.tag = tag;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return receiver + "[" + tag + "]";
	}
}
