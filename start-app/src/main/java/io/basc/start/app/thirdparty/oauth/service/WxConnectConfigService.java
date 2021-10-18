package io.basc.start.app.thirdparty.oauth.service;

import io.basc.start.app.thirdparty.oauth.pojo.WxConnectConfig;

public interface WxConnectConfigService {
	WxConnectConfig getById(int id);

	/**
	 * 保存或更新，如果是保存不用传id
	 * @param config
	 */
	void saveOrUpdateConfig(WxConnectConfig config);

	boolean deleteConfig(int id);
}
