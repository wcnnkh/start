package io.basc.start.app.event;

import io.basc.framework.context.annotation.Service;
import io.basc.framework.event.EventListener;
import io.basc.framework.event.EventRegistration;
import io.basc.framework.event.support.SimpleNamedEventDispatcher;

@Service
public class DefaultAppEventDispatcher implements AppEventDispatcher {
	@SuppressWarnings("rawtypes")
	private SimpleNamedEventDispatcher<Class, AppEvent<?>> dispatcher = new SimpleNamedEventDispatcher<Class, AppEvent<?>>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> EventRegistration registerListener(Class<T> type, EventListener<AppEvent<T>> eventListener) {
		return dispatcher.registerListener(type, (EventListener) eventListener);
	}

	public <T> void publishEvent(final Class<T> type, final AppEvent<T> event) {
		dispatcher.publishEvent(type, event);
	}

}
