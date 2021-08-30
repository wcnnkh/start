package io.basc.satrt.manage.web.editable.support;

import io.basc.framework.context.annotation.Provider;
import io.basc.satrt.manage.web.editable.DataManager;
import io.basc.satrt.manage.web.editable.Editor;
import io.basc.satrt.manage.web.editable.EditorResolver;
import io.basc.satrt.manage.web.editable.annotation.Editable;
import io.basc.start.user.security.SecurityProperties;

import java.util.Arrays;
import java.util.List;

@Provider
public class DefaultEditorResolver implements EditorResolver {
	private final DataManager dataManager;
	private final SecurityProperties securityProperties;

	public DefaultEditorResolver(DataManager dataManager, SecurityProperties securityProperties) {
		this.dataManager = dataManager;
		this.securityProperties = securityProperties;
	}

	@Override
	public boolean canResolve(Class<?> clazz) {
		return clazz.isAnnotationPresent(Editable.class);
	}

	@Override
	public List<Editor> resolve(Class<?> clazz) {
		EditorParent parent = new EditorParent(dataManager, clazz, securityProperties);
		EditorAddPage addPage = new EditorAddPage(dataManager, clazz, securityProperties);
		EditorInfoPage infoPage = new EditorInfoPage(dataManager, clazz, securityProperties);
		EditorAdd add = new EditorAdd(dataManager, clazz, securityProperties);
		EditorUpdate update = new EditorUpdate(dataManager, clazz, securityProperties);
		EditorDelete delete = new EditorDelete(dataManager, clazz, securityProperties);
		return Arrays.asList(parent, addPage, infoPage, add, update, delete);
	}

}
