package io.basc.satrt.app.admin.editable.support;

import java.util.Arrays;
import java.util.List;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.orm.repository.RepositoryTemplate;
import io.basc.satrt.app.admin.editable.Editor;
import io.basc.satrt.app.admin.editable.EditorMapper;
import io.basc.start.editable.support.DefaultEditableMapper;
import io.basc.start.usercenter.security.UsercenterSecurityConfigure;

@Provider(value = EditorMapper.class)
public class DefaultEditorResolver extends DefaultEditableMapper implements EditorMapper {
	private final RepositoryTemplate template;
	private final UsercenterSecurityConfigure appConfigure;
	private final ResultFactory resultFactory;

	public DefaultEditorResolver(RepositoryTemplate template, UsercenterSecurityConfigure appConfigure,
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
