package io.basc.start.tencent.wx;

import java.util.concurrent.TimeUnit;

import io.basc.framework.data.TemporaryStorageOperations;
import io.basc.framework.locks.LockFactory;
import io.basc.framework.locks.NoOpLockFactory;
import io.basc.framework.locks.UnableToAcquireLockException;
import io.basc.framework.security.Token;
import io.basc.framework.util.Assert;

public class ClusterWeiXinApi extends WeiXinApi {
	private LockFactory lockFactory = NoOpLockFactory.NO;
	private TemporaryStorageOperations storageOperations;
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

	public TemporaryStorageOperations getStorageOperations() {
		return storageOperations;
	}

	public void setStorageOperations(TemporaryStorageOperations storageOperations) {
		this.storageOperations = storageOperations;
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
		Token token = (storageOperations == null || forceUpdate) ? null : storageOperations.get(Token.class, cacheKey);
		if (forceUpdate || isInvalidToken(token)) {
			try {
				token = lockFactory.process(cacheKey + ":lock", tryLockTimeout, tryLockTimeoutUnit, () -> {
					Token target = (storageOperations == null || forceUpdate) ? null
							: storageOperations.get(Token.class, cacheKey);
					if (forceUpdate || isInvalidToken(target)) {
						target = super.getToken(grantType, forceUpdate);
						if (storageOperations != null) {
							storageOperations.set(cacheKey, target, target.getPeriodOfValidity(),
									TimeUnit.MILLISECONDS);
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
		Token token = (storageOperations == null || forceUpdate) ? null : storageOperations.get(Token.class, cacheKey);
		if (forceUpdate || isInvalidToken(token)) {
			try {
				token = lockFactory.process(cacheKey + ":lock", tryLockTimeout, tryLockTimeoutUnit, () -> {
					Token target = (storageOperations == null || forceUpdate) ? null
							: storageOperations.get(Token.class, cacheKey);
					if (forceUpdate || isInvalidToken(target)) {
						target = super.getTicket(type, forceUpdate);
						if (storageOperations != null) {
							storageOperations.set(cacheKey, target, target.getPeriodOfValidity(),
									TimeUnit.MILLISECONDS);
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
