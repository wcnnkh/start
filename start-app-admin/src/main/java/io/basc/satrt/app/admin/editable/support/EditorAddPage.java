package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.orm.repository.CurdRepository;
import io.basc.framework.web.message.model.ModelAndView;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.editable.EditableMapper;

public class EditorAddPage extends EditorCURD {

	public EditorAddPage(EditableMapper mapper,
			CurdRepository repository,
			Class<?> editableClass, AppConfigure appConfigure,
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
				"/io/basc/start/app/admin/web/editable/add.ftl");
		page.put("fields", getInputs(requestBean));
		page.put("info", requestBean);
		return page;
	}
}
