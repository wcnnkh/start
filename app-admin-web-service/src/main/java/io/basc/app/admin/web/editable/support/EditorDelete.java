package io.basc.app.admin.web.editable.support;

import io.basc.app.admin.web.editable.DataManager;
import io.basc.app.user.security.SecurityProperties;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;

public class EditorDelete extends EditorCURD {

	public EditorDelete(DataManager dataManager, Class<?> editableClass, SecurityProperties securityProperties) {
		super(dataManager, editableClass, HttpMethod.POST, securityProperties, "delete");
	}
	
	@Override
	public String getName() {
		return super.getName() + "(删除)";
	}

	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		return getDataManager().delete(getEditableClass(), requestBean);
	}
}
