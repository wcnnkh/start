package io.basc.start.verificationcode.support;

import java.io.Serializable;

import javax.validation.constraints.Null;

import io.basc.framework.util.Status;
import io.basc.framework.util.StringUtils;
import io.basc.framework.util.XUtils;

public class VerificationCodeConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 每天发送限制
	 */
	private int everyDayMaxSize = 10;
	/**
	 * 两次发送时间限制(秒)
	 */
	private int maxTimeInterval = 30;
	/**
	 * 验证码有效时间(秒)
	 */
	private int maxActiveTime = 300;
	/**
	 * 随机的验证码长度
	 */
	private int codeLength = 6;
	/**
	 * 是否是测试模式，测试模式不真实发送
	 */
	private boolean test = false;

	/**
	 * 检查后删除
	 */
	private boolean deleteAfterCheck = true;

	public int getEveryDayMaxSize() {
		return everyDayMaxSize;
	}

	public void setEveryDayMaxSize(int everyDayMaxSize) {
		this.everyDayMaxSize = everyDayMaxSize;
	}

	public int getMaxTimeInterval() {
		return maxTimeInterval;
	}

	public void setMaxTimeInterval(int maxTimeInterval) {
		this.maxTimeInterval = maxTimeInterval;
	}

	public int getMaxActiveTime() {
		return maxActiveTime;
	}

	public void setMaxActiveTime(int maxActiveTime) {
		this.maxActiveTime = maxActiveTime;
	}

	public int getCodeLength() {
		return codeLength;
	}

	public void setCodeLength(int codeLength) {
		this.codeLength = codeLength;
	}

	public boolean isTest() {
		return test;
	}

	public void setTest(boolean test) {
		this.test = test;
	}

	public boolean isDeleteAfterCheck() {
		return deleteAfterCheck;
	}

	public void setDeleteAfterCheck(boolean deleteAfterCheck) {
		this.deleteAfterCheck = deleteAfterCheck;
	}

	public Status<String> check(VerificationCode info) {
		if (getMaxTimeInterval() > 0
				&& (System.currentTimeMillis() - info.getLastSendTime()) < getMaxTimeInterval() * 1000L) {
			return XUtils.status(false, "发送过于频繁");
		}

		int count = info.getCount();
		if (getEveryDayMaxSize() > 0 && (count > getEveryDayMaxSize())) {
			return XUtils.status(false, "今日发送次数过多");
		}

		return XUtils.status(true, null);
	}

	public Status<String> check(String code, @Null VerificationCode info) {
		if (info == null) {
			return XUtils.status(false, "验证码错误");
		}

		if (StringUtils.isEmpty(code)) {
			return XUtils.status(false, "参数错误");
		}

		if (getMaxActiveTime() > 0
				&& (System.currentTimeMillis() - info.getLastSendTime()) > getMaxActiveTime() * 1000L) {
			return XUtils.status(false, "验证码已过期");
		}

		if (!code.equals(info.getCode())) {
			return XUtils.status(false, "验证码错误");
		}
		return XUtils.status(true, null);
	}
}
