package io.basc.satrt.manage.web.editable.support;

import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.web.model.ModelAndView;
import io.basc.satrt.manage.web.editable.DataManager;
import io.basc.start.user.security.SecurityProperties;

public class EditorAddPage extends EditorCURD {

	public EditorAddPage(DataManager dataManager, Class<?> editableClass, SecurityProperties securityProperties) {
		super(dataManager, editableClass, HttpMethod.GET, securityProperties, "add");
	}
	
	@Override
	public String getName() {
		return super.getName() + "(添加页面)";
	}

	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		ModelAndView page = new ModelAndView("/io/basc/start/manage/web/editable/add.ftl");
		page.put("fields", getInputs(requestBean));
		return page;
	}
}
