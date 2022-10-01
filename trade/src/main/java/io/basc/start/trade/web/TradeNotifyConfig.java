package io.basc.start.trade.web;

import io.basc.framework.context.ioc.annotation.Value;
import io.basc.framework.orm.annotation.ConfigurationProperties;
import io.basc.framework.util.StringUtils;

@ConfigurationProperties(prefix = "trade.notify")
public final class TradeNotifyConfig {
	public static final String CONTROLLER_PREFIX = "${trade.notify.controller:/trade/notify}";

	@Value(value = CONTROLLER_PREFIX, listener = false)
	private String controllerPrefix;
	
	private String host;
	
	public String getControllerPrefix() {
		return controllerPrefix;
	}

	public void setControllerPrefix(String controllerPrefix) {
		this.controllerPrefix = controllerPrefix;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
	/**
	 * 获取通知地址
	 * @see TradeNotifyController#notify(String, String, io.basc.framework.web.ServerHttpRequest)
	 * @param tradeMethod
	 * @return
	 */
	public String getNotifyUrl(String tradeMethod, String tradeStatus){
		return StringUtils.cleanPath(getHost() + "/" + getControllerPrefix() + "/" + tradeMethod + "/" + tradeStatus);
	}
}
