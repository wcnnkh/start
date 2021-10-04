package io.basc.start.verificationcode.support;

import java.io.Serializable;

public final class VerificationCode implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 最后一次发送时间
	 */
	private long lastSendTime;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 发送次数
	 */
	private int count;

	public long getLastSendTime() {
		return lastSendTime;
	}

	public void setLastSendTime(long lastSendTime) {
		this.lastSendTime = lastSendTime;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}