package io.basc.start.thirdparty.oauth2.service;

import io.basc.start.thirdparty.oauth2.dto.WeixinMpConfig;

public interface WeixinMpConfigService{
	WeixinMpConfig getConfig(String appid);
	
	void saveOrUpdateConfig(WeixinMpConfig config);
	
	boolean deleteConfig(String appid);
}
