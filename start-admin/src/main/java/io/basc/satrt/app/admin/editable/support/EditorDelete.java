package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.orm.repository.CurdOperations;
import io.basc.start.editable.EditableMapper;
import io.basc.start.usercenter.security.UsercenterSecurityConfigure;

public class EditorDelete extends EditorCURD {

	public EditorDelete(EditableMapper mapper,
			CurdOperations repository, Class<?> editableClass,
			UsercenterSecurityConfigure appConfigure, ResultFactory resultFactory) {
		super(mapper, repository, editableClass, appConfigure,
				HttpMethod.POST, resultFactory, "delete");
	}

	@Override
	public String getName() {
		return super.getName() + "(删除)";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		boolean success = getRepository().delete(
				(Class) getEditableClass(), requestBean);
		return success ? getResultFactory().success() : getResultFactory()
				.error("操作失败");
	}
}
