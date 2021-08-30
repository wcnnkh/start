package io.basc.start.vc.service;

import io.basc.framework.context.result.Result;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.core.utils.StringUtils;
import io.basc.framework.core.utils.XTime;
import io.basc.framework.data.TemporaryStorage;
import io.basc.framework.logger.Levels;
import io.basc.framework.logger.Logger;
import io.basc.framework.logger.LoggerFactory;
import io.basc.framework.util.RandomUtils;
import io.basc.start.vc.enums.VerificationCodeType;
import io.basc.start.vc.model.VerificationCodeInfo;

public abstract class AbstractVerificationCodeService {
	private static final int ONE_DAY = (int) (XTime.ONE_DAY / 1000);
	private static Logger logger = LoggerFactory.getLogger(AbstractVerificationCodeService.class);

	private TemporaryStorage temporaryCache;
	private int everyDayMaxSize = 10;// 每天发送限制
	private int maxTimeInterval = 30;// 两次发送时间限制(秒)
	private int maxActiveTime = 300;// 验证码有效时间(秒)
	// 验证码长度
	private int codeLength = 6;
	private boolean test = false;// 是否是测试模式，测试模式不真实发送
	private ResultFactory resultFactory;

	public AbstractVerificationCodeService(TemporaryStorage temporaryCache, ResultFactory resultFactory) {
		this.temporaryCache = temporaryCache;
		this.resultFactory = resultFactory;
	}

	protected String getCacheKey(String user, VerificationCodeType type) {
		return getClass().getName() + ":" + type + ":" + user;
	}

	public TemporaryStorage getTemporaryCache() {
		return temporaryCache;
	}

	public void setTemporaryCache(TemporaryStorage temporaryCache) {
		this.temporaryCache = temporaryCache;
	}

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

	public ResultFactory getResultFactory() {
		return resultFactory;
	}

	public void setResultFactory(ResultFactory resultFactory) {
		this.resultFactory = resultFactory;
	}

	public Result send(String user, VerificationCodeType type) {
		String key = getCacheKey(user, type);
		VerificationCodeInfo info = temporaryCache.getAndTouch(key, ONE_DAY);
		if (info == null || isTest()) {
			info = new VerificationCodeInfo();
		}

		long lastSendTime = info.getLastSendTime();
		if (getMaxTimeInterval() > 0 && (System.currentTimeMillis() - lastSendTime) < getMaxTimeInterval() * 1000L) {
			return resultFactory.error("发送过于频繁");
		}

		int count = info.getCount();
		if (getEveryDayMaxSize() > 0 && (count > getEveryDayMaxSize())) {
			return resultFactory.error("今日发送次数过多");
		}

		String code = RandomUtils.getNumCode(getCodeLength());
		Result result = resultFactory.success();
		Levels level = isTest() ? Levels.INFO : Levels.DEBUG;
		if (logger.isLoggable(level.getValue())) {
			logger.log(level.getValue(), (isTest() ? "测试模式" : "") + "向用户[{}]发送验证码[{}]", user, code);
		}

		if (!isTest()) {
			result = sendInternal(user, code, type);
			if (result.isError()) {
				return result;
			}
		}

		if (System.currentTimeMillis() - info.getLastSendTime() > XTime.ONE_DAY) {
			info.setCount(1);
		} else {
			info.setCount(info.getCount() + 1);
		}

		info.setCode(code);
		info.setLastSendTime(System.currentTimeMillis());
		temporaryCache.set(key, ONE_DAY, info);
		return isTest() ? resultFactory.success(code) : resultFactory.success();
	}

	protected abstract Result sendInternal(String user, String code, VerificationCodeType type);

	public Result check(String user, String code, VerificationCodeType type) {
		if (StringUtils.isEmpty(user, code)) {
			return resultFactory.parameterError();
		}

		String key = getCacheKey(user, type);
		VerificationCodeInfo info = temporaryCache.get(key);
		if (info == null) {
			return resultFactory.error("验证码错误(Not sent)");
		}

		if (getMaxActiveTime() > 0
				&& (System.currentTimeMillis() - info.getLastSendTime()) > getMaxActiveTime() * 1000L) {
			return resultFactory.error("验证码错误(Expired)");// 验证码已过期
		}

		if (!code.equals(info.getCode())) {
			return resultFactory.error("验证码错误");
		}

		info.setCode(null);
		temporaryCache.set(key, ONE_DAY, info);
		return resultFactory.success();
	}

}
