package scw.integration.trade.status;

import io.basc.framework.event.EventListener;
import io.basc.framework.event.EventRegistration;
import io.basc.framework.event.NamedEventDispatcher;

/**
 * 交易状态事件分发
 * 
 * @author shuchaowen
 *
 */
public interface TradeStatusDispatcher extends
		NamedEventDispatcher<String, TradeResultsEvent> {
	@Override
	EventRegistration registerListener(String status,
			EventListener<TradeResultsEvent> eventListener);

	@Override
	void publishEvent(String status, TradeResultsEvent event);
}
