package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.orm.repository.CurdRepositoryRegistry;
import io.basc.satrt.app.admin.editable.Editor;
import io.basc.satrt.app.admin.editable.EditorMapper;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.editable.support.DefaultEditableMapper;

import java.util.Arrays;
import java.util.List;

@Provider
public class DefaultEditorResolver extends DefaultEditableMapper implements
		EditorMapper {
	private final CurdRepositoryRegistry curdRepositoryRegistry;
	private final AppConfigure appConfigure;
	private final ResultFactory resultFactory;

	public DefaultEditorResolver(CurdRepositoryRegistry curdRepositoryRegistry,
			AppConfigure appConfigure, ResultFactory resultFactory) {
		this.curdRepositoryRegistry = curdRepositoryRegistry;
		this.appConfigure = appConfigure;
		this.resultFactory = resultFactory;
	}

	@Override
	public List<Editor> resolve(Class<?> clazz) {
		EditorParent parent = new EditorParent(this, curdRepositoryRegistry,
				clazz, appConfigure);
		EditorAddPage addPage = new EditorAddPage(this, curdRepositoryRegistry,
				clazz, appConfigure, resultFactory);
		EditorInfoPage infoPage = new EditorInfoPage(this,
				curdRepositoryRegistry, clazz, appConfigure, resultFactory);
		EditorAdd add = new EditorAdd(this, curdRepositoryRegistry, clazz,
				appConfigure, resultFactory);
		EditorUpdate update = new EditorUpdate(this, curdRepositoryRegistry,
				clazz, appConfigure, resultFactory);
		EditorDelete delete = new EditorDelete(this, curdRepositoryRegistry,
				clazz, appConfigure, resultFactory);
		return Arrays.asList(parent, addPage, infoPage, add, update, delete);
	}

}
