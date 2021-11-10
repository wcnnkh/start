package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.web.message.model.ModelAndView;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.data.DataService;

public class EditorAddPage extends EditorCURD {

	public EditorAddPage(DataService dataService, Class<?> editableClass, AppConfigure appConfigure,
			ResultFactory resultFactory) {
		super(dataService, editableClass, HttpMethod.GET, appConfigure, resultFactory, "add");
	}
	
	@Override
	public String getName() {
		return super.getName() + "(添加页面)";
	}

	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		ModelAndView page = new ModelAndView("/io/basc/start/app/admin/web/editable/add.ftl");
		page.put("fields", getInputs(requestBean));
		return page;
	}
}
