package io.basc.start.app.thirdparty.oauth.service;

import io.basc.start.app.thirdparty.oauth.pojo.WeixinMpConfig;

public interface WeixinMpConfigService{
	WeixinMpConfig getConfig(String appid);
	
	void saveOrUpdateConfig(WeixinMpConfig config);
	
	boolean deleteConfig(String appid);
}
