package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.web.message.model.ModelAndView;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.data.DataService;

public class EditorInfoPage extends EditorCURD {

	public EditorInfoPage(DataService dataService, Class<?> editableClass, AppConfigure appConfigure, ResultFactory resultFactory) {
		super(dataService, editableClass, HttpMethod.GET, appConfigure, resultFactory, "info");
	}

	@Override
	public String getName() {
		return super.getName() + "(查看)";
	}

	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		Object info = getDataService().getByPrimaryKeys(getEditableClass(), requestBean);
		ModelAndView page = new ModelAndView("/io/basc/start/app/admin/web/editable/info.ftl");
		page.put("info", info);
		page.put("query", requestBean);
		page.put("fields", getInputs(requestBean));
		return page;
	}
}
