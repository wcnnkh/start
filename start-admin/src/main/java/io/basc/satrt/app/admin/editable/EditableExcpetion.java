package io.basc.satrt.app.admin.editable;

import io.basc.framework.lang.NestedRuntimeException;

public class EditableExcpetion extends NestedRuntimeException {
	private static final long serialVersionUID = 1L;

	public EditableExcpetion(String msg) {
		super(msg);
	}

	public EditableExcpetion(Throwable cause) {
		super(cause);
	}

	public EditableExcpetion(String message, Throwable cause) {
		super(message, cause);
	}
}
