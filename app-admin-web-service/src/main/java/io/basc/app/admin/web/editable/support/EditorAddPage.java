package io.basc.app.admin.web.editable.support;

import io.basc.app.admin.web.editable.DataManager;
import io.basc.app.user.security.SecurityProperties;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.web.model.Page;

public class EditorAddPage extends EditorCURD {

	public EditorAddPage(DataManager dataManager, Class<?> editableClass, SecurityProperties securityProperties) {
		super(dataManager, editableClass, HttpMethod.GET, securityProperties, "add");
	}
	
	@Override
	public String getName() {
		return super.getName() + "(添加页面)";
	}

	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		Page page = new Page("/admin/editable/add.ftl");
		page.put("fields", getInputs(requestBean));
		return page;
	}
}
