package io.basc.start.trade.status;

import io.basc.framework.event.BroadcastNamedEventDispatcher;
import io.basc.framework.event.EventListener;
import io.basc.framework.util.Registration;

/**
 * 交易状态事件分发
 * 
 * @author shuchaowen
 *
 */
public interface TradeStatusDispatcher extends BroadcastNamedEventDispatcher<String, TradeResultsEvent> {
	@Override
	Registration registerListener(String status, EventListener<TradeResultsEvent> eventListener);

	@Override
	void publishEvent(String status, TradeResultsEvent event);
}
