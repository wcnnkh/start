package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.satrt.app.admin.editable.DataManager;
import io.basc.start.app.user.security.SecurityProperties;

public class EditorUpdate extends EditorCURD {

	public EditorUpdate(DataManager dataManager, Class<?> editableClass, SecurityProperties securityProperties) {
		super(dataManager, editableClass, HttpMethod.POST, securityProperties, "update");
	}

	@Override
	public String getName() {
		return super.getName() + "(修改)";
	}

	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		return getDataManager().update(getEditableClass(), requestBean);
	}
}
