package io.basc.start.app.event;

import io.basc.framework.event.EventListener;
import io.basc.framework.util.Registration;

public interface AppEventDispatcher {
	<T> Registration registerListener(Class<T> type, EventListener<AppEvent<T>> eventListener);

	<T> void publishEvent(Class<T> type, AppEvent<T> event);
}
