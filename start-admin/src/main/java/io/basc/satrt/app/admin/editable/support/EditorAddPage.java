package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.orm.repository.CurdOperations;
import io.basc.framework.web.message.model.ModelAndView;
import io.basc.start.editable.EditableMapper;
import io.basc.start.usercenter.security.UsercenterSecurityConfigure;

public class EditorAddPage extends EditorCURD {

	public EditorAddPage(EditableMapper mapper,
			CurdOperations repository,
			Class<?> editableClass, UsercenterSecurityConfigure appConfigure,
			ResultFactory resultFactory) {
		super(mapper, repository, editableClass, appConfigure,
				HttpMethod.GET, resultFactory, "add");
	}

	@Override
	public String getName() {
		return super.getName() + "(添加页面)";
	}

	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		ModelAndView page = new ModelAndView(
				"/io/basc/start/admin/web/editable/add.ftl");
		page.put("fields", getInputs(requestBean));
		page.put("info", requestBean);
		return page;
	}
}
