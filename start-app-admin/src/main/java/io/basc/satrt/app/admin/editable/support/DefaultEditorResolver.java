package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.context.result.ResultFactory;
import io.basc.satrt.app.admin.editable.Editor;
import io.basc.satrt.app.admin.editable.EditorResolver;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.data.DataService;
import io.basc.start.data.annotation.Editable;

import java.util.Arrays;
import java.util.List;

@Provider
public class DefaultEditorResolver implements EditorResolver {
	private final DataService dataService;
	private final AppConfigure appConfigure;
	private final ResultFactory resultFactory;

	public DefaultEditorResolver(DataService dataService, AppConfigure appConfigure,
			ResultFactory resultFactory) {
		this.dataService = dataService;
		this.appConfigure = appConfigure;
		this.resultFactory = resultFactory;
	}

	@Override
	public boolean canResolve(Class<?> clazz) {
		return clazz.isAnnotationPresent(Editable.class);
	}

	@Override
	public List<Editor> resolve(Class<?> clazz) {
		EditorParent parent = new EditorParent(dataService, clazz, appConfigure);
		EditorAddPage addPage = new EditorAddPage(dataService, clazz, appConfigure, resultFactory);
		EditorInfoPage infoPage = new EditorInfoPage(dataService, clazz, appConfigure, resultFactory);
		EditorAdd add = new EditorAdd(dataService, clazz, appConfigure, resultFactory);
		EditorUpdate update = new EditorUpdate(dataService, clazz, appConfigure, resultFactory);
		EditorDelete delete = new EditorDelete(dataService, clazz, appConfigure, resultFactory);
		return Arrays.asList(parent, addPage, infoPage, add, update, delete);
	}

}
