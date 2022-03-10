package io.basc.start.tencent.wx.api;

import java.util.concurrent.TimeUnit;

import io.basc.framework.data.TemporaryDataOperations;
import io.basc.framework.locks.LockFactory;
import io.basc.framework.locks.NoOpLockFactory;
import io.basc.framework.locks.UnableToAcquireLockException;
import io.basc.framework.security.Token;
import io.basc.framework.util.Assert;
import io.basc.start.tencent.wx.WeiXinException;

public class ClusterWeiXinApi extends WeiXinApi {
	private LockFactory lockFactory = NoOpLockFactory.NO;
	private TemporaryDataOperations dataOperations;
	private long tryLockTimeout = 1;
	private TimeUnit tryLockTimeoutUnit = TimeUnit.SECONDS;

	public ClusterWeiXinApi(String appid, String appsecret) {
		super(appid, appsecret);
	}

	public LockFactory getLockFactory() {
		return lockFactory;
	}

	public void setLockFactory(LockFactory lockFactory) {
		this.lockFactory = lockFactory;
	}

	public final TemporaryDataOperations getDataOperations() {
		return dataOperations;
	}

	public void setDataOperations(TemporaryDataOperations dataOperations) {
		this.dataOperations = dataOperations;
	}

	public long getTryLockTimeout() {
		return tryLockTimeout;
	}

	public void setTryLockTimeout(long tryLockTimeout) {
		this.tryLockTimeout = tryLockTimeout;
	}

	public TimeUnit getTryLockTimeoutUnit() {
		return tryLockTimeoutUnit;
	}

	public void setTryLockTimeoutUnit(TimeUnit tryLockTimeoutUnit) {
		Assert.requiredArgument(tryLockTimeoutUnit != null, "tryLockTimeoutUnit");
		this.tryLockTimeoutUnit = tryLockTimeoutUnit;
	}

	@Override
	public Token getToken(String grantType, boolean forceUpdate)
			throws UnableToAcquireLockException, WeiXinApiException {
		String cacheKey = "weixin:cgi-bin:token:" + grantType + ":" + getAppid();
		Token token = (dataOperations == null || forceUpdate) ? null : dataOperations.get(Token.class, cacheKey);
		if (forceUpdate || isInvalidToken(token)) {
			try {
				token = lockFactory.process(cacheKey + ":lock", tryLockTimeout, tryLockTimeoutUnit, () -> {
					Token target = (dataOperations == null || forceUpdate) ? null
							: dataOperations.get(Token.class, cacheKey);
					if (forceUpdate || isInvalidToken(target)) {
						target = super.getToken(grantType, forceUpdate);
						if (dataOperations != null) {
							dataOperations.set(cacheKey, target, target.getPeriodOfValidity(), TimeUnit.MILLISECONDS);
						}
					}
					return target;
				});
			} catch (InterruptedException e) {
				throw new WeiXinException(cacheKey, e);
			}
		}
		return token;
	}

	@Override
	public Token getTicket(String type, boolean forceUpdate) throws WeiXinApiException {
		String cacheKey = "weixin:cgi-bin:ticket:" + type + ":" + getAppid();
		Token token = (dataOperations == null || forceUpdate) ? null : dataOperations.get(Token.class, cacheKey);
		if (forceUpdate || isInvalidToken(token)) {
			try {
				token = lockFactory.process(cacheKey + ":lock", tryLockTimeout, tryLockTimeoutUnit, () -> {
					Token target = (dataOperations == null || forceUpdate) ? null
							: dataOperations.get(Token.class, cacheKey);
					if (forceUpdate || isInvalidToken(target)) {
						target = super.getTicket(type, forceUpdate);
						if (dataOperations != null) {
							dataOperations.set(cacheKey, target, target.getPeriodOfValidity(), TimeUnit.MILLISECONDS);
						}
					}
					return target;
				});
			} catch (InterruptedException e) {
				throw new WeiXinException(cacheKey, e);
			}
		}
		return token;
	}
}
