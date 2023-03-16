package io.basc.start.trade.status;

import io.basc.framework.context.annotation.Provider;
import io.basc.framework.core.Ordered;
import io.basc.framework.event.support.StandardBroadcastNamedEventDispatcher;

/**
 * 交易状态分发的默认实现
 * 
 * @author shuchaowen
 *
 */
@Provider(order = Ordered.LOWEST_PRECEDENCE)
public class DefaultTradeStatusDispatcher extends StandardBroadcastNamedEventDispatcher<String, TradeResultsEvent>
		implements TradeStatusDispatcher {
}
