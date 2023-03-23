package io.basc.start.template.event;

import io.basc.framework.event.ChangeEvent;
import io.basc.framework.event.ChangeType;

public class AppEvent<T> extends ChangeEvent<T> {
	private static final long serialVersionUID = 1L;

	public AppEvent(T source, ChangeType changeType) {
		super(changeType, source);
	}
}
