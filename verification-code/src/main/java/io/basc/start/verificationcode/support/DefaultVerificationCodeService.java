package io.basc.start.verificationcode.support;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.core.Ordered;
import io.basc.framework.data.memory.MemoryDataOperations;
import io.basc.framework.factory.ConfigurableServices;
import io.basc.framework.logger.Levels;
import io.basc.framework.logger.Logger;
import io.basc.framework.logger.LoggerFactory;
import io.basc.framework.util.RandomUtils;
import io.basc.framework.util.Status;
import io.basc.framework.util.TimeUtils;
import io.basc.framework.util.XUtils;
import io.basc.start.verificationcode.Receiver;
import io.basc.start.verificationcode.VerificationCodeSender;
import io.basc.start.verificationcode.VerificationCodeService;

@Provider(order = Ordered.LOWEST_PRECEDENCE, value = VerificationCodeService.class)
public class DefaultVerificationCodeService extends ConfigurableServices<VerificationCodeSender>
		implements VerificationCodeService, VerificationCodeSender {
	private static Logger logger = LoggerFactory.getLogger(DefaultVerificationCodeService.class);

	private VerificationCodeConfiguration configuration;
	private final VerificationCodeStorage strategy;

	public DefaultVerificationCodeService() {
		this(new DefaultVerificationCodeStorage(new MemoryDataOperations()));
	}

	public DefaultVerificationCodeService(VerificationCodeStorage strategy) {
		super(VerificationCodeSender.class);
		this.strategy = strategy;
	}

	@Override
	public boolean canSend(Receiver receiver) {
		for (VerificationCodeSender sender : this) {
			if (sender.canSend(receiver)) {
				return true;
			}
		}
		return false;
	}

	public VerificationCodeConfiguration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(VerificationCodeConfiguration configuration) {
		this.configuration = configuration;
	}

	public VerificationCodeStorage getStrategy() {
		return strategy;
	}

	@Override
	public Status<String> send(Receiver receiver) {
		String code = RandomUtils.getNumCode(getConfiguration().getCodeLength());
		Status<String> status = send(code, receiver);
		if (status.isActive()) {
			return XUtils.status(true, code);
		}
		return status;
	}

	@Override
	public Status<String> send(String code, Receiver receiver) {
		VerificationCode verificationCode = getStrategy().getVerificationCode(receiver);
		if (verificationCode == null) {
			verificationCode = new VerificationCode();
		}

		Status<String> status = getConfiguration().check(verificationCode);
		if (!status.isActive()) {
			return status;
		}

		status = send(code, getConfiguration().isTest(), receiver);
		if (!status.isActive()) {
			return status;
		}

		if (System.currentTimeMillis() - verificationCode.getLastSendTime() > TimeUtils.ONE_DAY) {
			verificationCode.setCount(1);
		} else {
			verificationCode.setCount(verificationCode.getCount() + 1);
		}

		verificationCode.setCode(code);
		verificationCode.setLastSendTime(System.currentTimeMillis());
		strategy.set(receiver, verificationCode, TimeUtils.ONE_DAY / 1000);
		return XUtils.status(true, code);
	}

	public Status<String> send(String code, boolean test, Receiver receiver) {
		Levels level = test ? Levels.INFO : Levels.DEBUG;
		if (logger.isLoggable(level.getValue())) {
			logger.log(level.getValue(), (test ? "测试模式" : "") + "向用户[{}]发送验证码[{}]", receiver, code);
		}

		if (test) {
			return XUtils.status(true, code);
		}

		for (VerificationCodeSender sender : this) {
			if (sender.canSend(receiver)) {
				return sender.send(code, receiver);
			}
		}
		return XUtils.status(false,
				"Not support send verification code [" + code + "] to [" + receiver.getReceiver() + "]");
	}

	@Override
	public Status<String> validate(String code, Receiver receiver) {
		VerificationCode info = getStrategy().getVerificationCode(receiver);
		Status<String> status = getConfiguration().check(code, info);
		if (status.isActive() && getConfiguration().isDeleteAfterCheck()) {
			getStrategy().delete(receiver);
		}
		return status;
	}
}
