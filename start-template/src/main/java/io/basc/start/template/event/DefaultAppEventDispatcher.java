package io.basc.start.template.event;

import io.basc.framework.context.annotation.Service;
import io.basc.framework.event.EventListener;
import io.basc.framework.event.support.StandardBroadcastNamedEventDispatcher;
import io.basc.framework.util.Registration;

@Service
public class DefaultAppEventDispatcher implements AppEventDispatcher {
	@SuppressWarnings("rawtypes")
	private StandardBroadcastNamedEventDispatcher<Class, AppEvent<?>> dispatcher = new StandardBroadcastNamedEventDispatcher<Class, AppEvent<?>>();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> Registration registerListener(Class<T> type, EventListener<AppEvent<T>> eventListener) {
		return dispatcher.registerListener(type, (EventListener) eventListener);
	}

	public <T> void publishEvent(final Class<T> type, final AppEvent<T> event) {
		dispatcher.publishEvent(type, event);
	}

}
