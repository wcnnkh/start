package io.basc.start.app.thirdparty.oauth.service.impl;

import io.basc.framework.beans.annotation.Service;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.db.DB;
import io.basc.start.app.configure.BaseServiceConfiguration;
import io.basc.start.app.thirdparty.oauth.pojo.WeixinMpConfig;
import io.basc.start.app.thirdparty.oauth.service.WeixinMpConfigService;

@Service
public class WeixinMpConfigServiceImpl extends BaseServiceConfiguration implements WeixinMpConfigService{

	public WeixinMpConfigServiceImpl(DB db, ResultFactory resultFactory) {
		super(db, resultFactory);
	}

	@Override
	public WeixinMpConfig getConfig(String appid) {
		return db.getById(WeixinMpConfig.class, appid);
	}

	@Override
	public void saveOrUpdateConfig(WeixinMpConfig config) {
		db.saveOrUpdate(config);
	}

	@Override
	public boolean deleteConfig(String appid) {
		return db.deleteById(WeixinMpConfig.class, appid);
	}

}
