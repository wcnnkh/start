package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.satrt.app.admin.editable.DataManager;
import io.basc.start.app.user.security.SecurityProperties;

public class EditorAdd extends EditorCURD {

	public EditorAdd(DataManager dataManager, Class<?> editableClass, SecurityProperties securityProperties) {
		super(dataManager, editableClass, HttpMethod.POST, securityProperties, "add");
	}

	@Override
	public String getName() {
		return super.getName() + "(添加)";
	}

	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		return getDataManager().add(getEditableClass(), requestBean);
	}

}
