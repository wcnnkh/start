package io.basc.satrt.manage.web.editable.support;

import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.satrt.manage.web.editable.DataManager;
import io.basc.start.user.security.SecurityProperties;

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
