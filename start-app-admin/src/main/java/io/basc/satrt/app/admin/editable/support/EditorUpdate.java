package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.data.DataService;

public class EditorUpdate extends EditorCURD {

	public EditorUpdate(DataService dataService, Class<?> editableClass, AppConfigure appConfigure, ResultFactory resultFactory) {
		super(dataService, editableClass, HttpMethod.POST, appConfigure, resultFactory, "update");
	}

	@Override
	public String getName() {
		return super.getName() + "(修改)";
	}

	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		boolean success = getDataService().update(getEditableClass(), requestBean);
		return response(success);
	}
}
