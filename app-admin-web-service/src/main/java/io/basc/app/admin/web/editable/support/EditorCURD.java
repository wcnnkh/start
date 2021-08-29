package io.basc.app.admin.web.editable.support;

import io.basc.app.admin.web.editable.DataManager;
import io.basc.app.user.security.SecurityProperties;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;

import java.util.Map;

public abstract class EditorCURD extends EditorParent {
	private final HttpMethod method;
	private final String name;

	public EditorCURD(DataManager dataManager, Class<?> editableClass, HttpMethod method, SecurityProperties securityProperties, String name) {
		super(dataManager, editableClass, securityProperties);
		this.method = method;
		this.name = name;
	}

	@Override
	public final String getMethod() {
		return method.name();
	}
	
	@Override
	public String getPath() {
		return getSecurityProperties().getController() + "/" + getEditableClass().getName() + "/" + name;
	}

	@Override
	public String getId() {
		return getEditableClass().getName() + "#" + method + "#" + name;
	}

	@Override
	public final String getParentId() {
		return super.getId();
	}

	@Override
	public final boolean isMenu() {
		return false;
	}

	@Override
	public Map<String, String> getAttributeMap() {
		return null;
	}

	@Override
	public abstract Object doAction(HttpChannel httpChannel);
}
