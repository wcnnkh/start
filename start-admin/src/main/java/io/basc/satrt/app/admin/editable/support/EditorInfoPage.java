package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.orm.repository.CurdOperations;
import io.basc.framework.web.message.model.ModelAndView;
import io.basc.start.editable.EditableMapper;
import io.basc.start.usercenter.security.UsercenterSecurityConfigure;

public class EditorInfoPage extends EditorCURD {

	public EditorInfoPage(EditableMapper mapper, CurdOperations repository, Class<?> editableClass,
			UsercenterSecurityConfigure appConfigure, ResultFactory resultFactory) {
		super(mapper, repository, editableClass, appConfigure, HttpMethod.GET, resultFactory, "info");
	}

	@Override
	public String getName() {
		return super.getName() + "(查看)";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		Object info = getRepository().query((Class) getEditableClass(), requestBean).findAny().get();
		ModelAndView page = new ModelAndView("/io/basc/start/admin/web/editable/info.ftl");
		page.put("info", info);
		page.put("query", requestBean);
		page.put("fields", getInputs(requestBean));
		return page;
	}
}
