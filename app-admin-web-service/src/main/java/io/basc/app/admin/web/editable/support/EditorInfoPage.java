package io.basc.app.admin.web.editable.support;

import io.basc.app.admin.web.editable.DataManager;
import io.basc.app.user.security.SecurityProperties;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.web.model.Page;

public class EditorInfoPage extends EditorCURD {

	public EditorInfoPage(DataManager dataManager, Class<?> editableClass, SecurityProperties securityProperties) {
		super(dataManager, editableClass, HttpMethod.GET, securityProperties, "info");
	}

	@Override
	public String getName() {
		return super.getName() + "(查看)";
	}

	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		Object info = getDataManager().info(getEditableClass(), requestBean);
		Page page = new Page("/admin/editable/info.ftl");
		page.put("info", info);
		page.put("query", requestBean);
		page.put("fields", getInputs(requestBean));
		return page;
	}
}
