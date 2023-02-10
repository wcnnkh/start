package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.orm.repository.CurdOperations;
import io.basc.start.editable.EditableMapper;
import io.basc.start.usercenter.security.UsercenterSecurityConfigure;

public class EditorAdd extends EditorCURD {

	public EditorAdd(EditableMapper mapper,
			CurdOperations repository, Class<?> editableClass,
			UsercenterSecurityConfigure appConfigure, ResultFactory resultFactory) {
		super(mapper, repository, editableClass, appConfigure,
				HttpMethod.POST, resultFactory, "add");
	}

	@Override
	public String getName() {
		return super.getName() + "(添加)";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		boolean success = getRepository().saveIfAbsent(
				(Class) getEditableClass(), requestBean);
		return response(success);
	}

}
