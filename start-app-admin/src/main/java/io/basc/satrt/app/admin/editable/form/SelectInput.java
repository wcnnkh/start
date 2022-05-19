package io.basc.satrt.app.admin.editable.form;

import io.basc.framework.util.Pair;

import java.util.List;

public class SelectInput extends Input {
	private static final long serialVersionUID = 1L;
	private List<Pair<String, String>> options;
	private Class<?> queryClass;
	private String queryName;

	public SelectInput() {
		super(InputType.SELECT);
	}

	public SelectInput(Class<?> queryClass, String queryName) {
		this();
		this.queryClass = queryClass;
		this.queryName = queryName;
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

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}
}
