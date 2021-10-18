package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.data.DataService;

public class EditorDelete extends EditorCURD {

	public EditorDelete(DataService dataService, Class<?> editableClass,
			AppConfigure appConfigure, ResultFactory resultFactory) {
		super(dataService, editableClass, HttpMethod.POST, appConfigure,
				resultFactory, "delete");
	}

	@Override
	public String getName() {
		return super.getName() + "(删除)";
	}

	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		boolean success = getDataService().delete(getEditableClass(),
				requestBean);
		return success ? getResultFactory().success() : getResultFactory()
				.error("操作失败");
	}
}
