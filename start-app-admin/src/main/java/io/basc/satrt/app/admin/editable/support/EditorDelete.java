package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.orm.repository.CurdRepository;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.editable.EditableMapper;

public class EditorDelete extends EditorCURD {

	public EditorDelete(EditableMapper mapper,
			CurdRepository repository, Class<?> editableClass,
			AppConfigure appConfigure, ResultFactory resultFactory) {
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
