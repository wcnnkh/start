package io.basc.start.app.thirdparty.oauth.service.impl;

import io.basc.framework.beans.annotation.Service;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.db.DB;
import io.basc.start.app.configure.BaseServiceConfiguration;
import io.basc.start.app.thirdparty.oauth.pojo.WxConnectConfig;
import io.basc.start.app.thirdparty.oauth.service.WxConnectConfigService;

@Service
public class WxConnectConfigServiceImpl extends BaseServiceConfiguration implements WxConnectConfigService{

	public WxConnectConfigServiceImpl(DB db, ResultFactory resultFactory) {
		super(db, resultFactory);
	}

	@Override
	public WxConnectConfig getById(int id) {
		return db.getById(WxConnectConfig.class, id);
	}

	@Override
	public void saveOrUpdateConfig(WxConnectConfig config) {
		db.saveOrUpdate(config);
	}

	@Override
	public boolean deleteConfig(int id) {
		return db.delete(WxConnectConfig.class, id);
	}

}
