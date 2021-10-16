package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.mvc.model.ModelAndView;
import io.basc.satrt.app.admin.editable.DataManager;
import io.basc.start.app.user.security.SecurityProperties;

public class EditorInfoPage extends EditorCURD {

	public EditorInfoPage(DataManager dataManager, Class<?> editableClass, SecurityProperties securityProperties) {
		super(dataManager, editableClass, HttpMethod.GET, securityProperties, "info");
	}

	@Override
	public String getName() {
		return super.getName() + "(查看)";
	}

	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		Object info = getDataManager().info(getEditableClass(), requestBean);
		ModelAndView page = new ModelAndView("/io/basc/start/manage/web/editable/info.ftl");
		page.put("info", info);
		page.put("query", requestBean);
		page.put("fields", getInputs(requestBean));
		return page;
	}
}
