package io.basc.integration.trade.web;

import io.basc.framework.web.ServerHttpRequest;
import io.basc.integration.trade.TradeAdapter;
import io.basc.integration.trade.TradeException;
import io.basc.integration.trade.status.TradeStatusDispatcher;

public interface TradeNotifyAdapter extends TradeAdapter{

	Object notify(String tradeMethod, String tradeStatus, ServerHttpRequest request,
			TradeStatusDispatcher dispatcher) throws TradeException;
}
