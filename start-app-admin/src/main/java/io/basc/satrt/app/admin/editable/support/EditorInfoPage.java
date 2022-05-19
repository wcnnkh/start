package io.basc.satrt.app.admin.editable.support;

import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.orm.repository.CurdRepositoryRegistry;
import io.basc.framework.web.message.model.ModelAndView;
import io.basc.start.app.configure.AppConfigure;
import io.basc.start.editable.EditableMapper;

public class EditorInfoPage extends EditorCURD {

	public EditorInfoPage(EditableMapper mapper,
			CurdRepositoryRegistry curdRepositoryRegistry,
			Class<?> editableClass, AppConfigure appConfigure,
			ResultFactory resultFactory) {
		super(mapper, curdRepositoryRegistry, editableClass, appConfigure,
				HttpMethod.GET, resultFactory, "info");
	}

	@Override
	public String getName() {
		return super.getName() + "(查看)";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Object doAction(HttpChannel httpChannel) {
		Object requestBean = httpChannel.getInstance(getEditableClass());
		Object info = getCurdRepositoryRegistry()
				.getCurdRepository((Class) getEditableClass())
				.queryAll(requestBean).findAny().get();
		ModelAndView page = new ModelAndView(
				"/io/basc/start/app/admin/web/editable/info.ftl");
		page.put("info", info);
		page.put("query", requestBean);
		page.put("fields", getInputs(requestBean));
		return page;
	}
}
