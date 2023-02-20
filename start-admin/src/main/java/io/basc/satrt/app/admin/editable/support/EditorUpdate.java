package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.orm.repository.CurdOperations;
import io.basc.start.editable.EditableMapper;
import io.basc.start.usercenter.security.UsercenterSecurityConfigure;

public class EditorUpdate extends EditorCURD {

	public EditorUpdate(EditableMapper mapper, CurdOperations repository, Class<?> editableClass,
			UsercenterSecurityConfigure appConfigure, ResultFactory resultFactory) {
		super(mapper, repository, editableClass, appConfigure, HttpMethod.POST, resultFactory, "update");
	}

	@Override
	public String getName() {
		return super.getName() + "(修改)";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		boolean success = getRepository().update((Class) getEditableClass(), requestBean);
		return response(success);
	}
}
