package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.orm.repository.RepositoryTemplate;
import io.basc.satrt.app.admin.editable.Editor;
import io.basc.satrt.app.admin.editable.EditorMapper;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.editable.support.DefaultEditableMapper;

import java.util.Arrays;
import java.util.List;

@Provider(value = EditorMapper.class)
public class DefaultEditorResolver extends DefaultEditableMapper implements EditorMapper {
	private final RepositoryTemplate template;
	private final AppConfigure appConfigure;
	private final ResultFactory resultFactory;

	public DefaultEditorResolver(RepositoryTemplate template, AppConfigure appConfigure,
			ResultFactory resultFactory) {
		this.template = template;
		this.appConfigure = appConfigure;
		this.resultFactory = resultFactory;
	}

	@Override
	public List<Editor> resolve(Class<?> clazz) {
		EditorParent parent = new EditorParent(this, template, clazz, appConfigure);
		EditorAddPage addPage = new EditorAddPage(this, template, clazz, appConfigure, resultFactory);
		EditorInfoPage infoPage = new EditorInfoPage(this, template, clazz, appConfigure, resultFactory);
		EditorAdd add = new EditorAdd(this, template, clazz, appConfigure, resultFactory);
		EditorUpdate update = new EditorUpdate(this, template, clazz, appConfigure, resultFactory);
		EditorDelete delete = new EditorDelete(this, template, clazz, appConfigure, resultFactory);
		return Arrays.asList(parent, addPage, infoPage, add, update, delete);
	}

}
