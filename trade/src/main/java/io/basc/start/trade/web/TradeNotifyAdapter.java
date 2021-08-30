package io.basc.start.trade.web;

import io.basc.framework.web.ServerHttpRequest;
import io.basc.start.trade.TradeAdapter;
import io.basc.start.trade.TradeException;
import io.basc.start.trade.status.TradeStatusDispatcher;

public interface TradeNotifyAdapter extends TradeAdapter{

	Object notify(String tradeMethod, String tradeStatus, ServerHttpRequest request,
			TradeStatusDispatcher dispatcher) throws TradeException;
}
