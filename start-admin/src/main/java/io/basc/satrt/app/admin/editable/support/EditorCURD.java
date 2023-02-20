package io.basc.satrt.app.admin.editable.support;

import java.util.Map;

import io.basc.framework.context.transaction.Result;
import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.orm.repository.CurdOperations;
import io.basc.start.editable.EditableMapper;
import io.basc.start.usercenter.security.UsercenterSecurityConfigure;

public abstract class EditorCURD extends EditorParent {
	private final HttpMethod method;
	private final String name;
	private final ResultFactory resultFactory;

	public EditorCURD(EditableMapper mapper, CurdOperations repository, Class<?> editableClass,
			UsercenterSecurityConfigure appConfigure, HttpMethod method, ResultFactory resultFactory, String name) {
		super(mapper, repository, editableClass, appConfigure);
		this.resultFactory = resultFactory;
		this.method = method;
		this.name = name;
	}

	public ResultFactory getResultFactory() {
		return resultFactory;
	}

	public Result response(boolean success) {
		return success ? resultFactory.success() : resultFactory.error("操作失败");
	}

	@Override
	public final String getMethod() {
		return method.name();
	}

	@Override
	public String getPath() {
		return getAppConfigure().getController() + "/editable/" + getEditableClass().getName() + "/" + name;
	}

	@Override
	public String getId() {
		return getEditableClass().getName() + "#" + method + "#" + name;
	}

	@Override
	public final String getParentId() {
		return super.getId();
	}

	@Override
	public final boolean isMenu() {
		return false;
	}

	@Override
	public Map<String, String> getAttributeMap() {
		return null;
	}

	@Override
	public abstract Object doAction(HttpChannel httpChannel);
}
