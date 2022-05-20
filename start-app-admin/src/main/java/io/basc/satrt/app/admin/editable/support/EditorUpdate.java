package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.orm.repository.CurdRepository;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.editable.EditableMapper;

public class EditorUpdate extends EditorCURD {

	public EditorUpdate(EditableMapper mapper,
			CurdRepository repository, Class<?> editableClass,
			AppConfigure appConfigure, ResultFactory resultFactory) {
		super(mapper, repository, editableClass, appConfigure,
				HttpMethod.POST, resultFactory, "update");
	}

	@Override
	public String getName() {
		return super.getName() + "(修改)";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		boolean success = getRepository().update(
				(Class) getEditableClass(), requestBean);
		return response(success);
	}
}
