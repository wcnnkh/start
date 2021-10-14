package io.basc.satrt.app.admin.editable.form;

import io.basc.framework.util.Pair;
import io.basc.satrt.app.admin.editable.annotation.Select;

import java.util.List;

public class SelectInput extends Input {
	private static final long serialVersionUID = 1L;
	private List<Pair<String, String>> options;
	private Class<?> queryClass;

	public SelectInput() {
		super(InputType.SELECT);
	}
	
	public SelectInput(Select select) {
		this();
		this.queryClass = select.value();
	}

	public List<Pair<String, String>> getOptions() {
		return options;
	}

	public void setOptions(List<Pair<String, String>> options) {
		this.options = options;
	}

	public Class<?> getQueryClass() {
		return queryClass;
	}

	public void setQueryClass(Class<?> queryClass) {
		this.queryClass = queryClass;
	}
}
