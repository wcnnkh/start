package io.basc.start.address.service.config;

import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.db.DB;

public class BaseServiceConfiguration {
	protected final DB db;
	protected final ResultFactory resultFactory;
	
	public BaseServiceConfiguration(DB db, ResultFactory resultFactory){
		this.db = db;
		this.resultFactory = resultFactory;
	}

	public DB getDb() {
		return db;
	}

	public ResultFactory getResultFactory() {
		return resultFactory;
	}
}
