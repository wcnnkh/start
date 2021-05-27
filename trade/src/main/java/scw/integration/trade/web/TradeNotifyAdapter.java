package scw.integration.trade.web;

import scw.integration.trade.TradeAdapter;
import scw.integration.trade.TradeException;
import scw.integration.trade.status.TradeStatusDispatcher;
import scw.web.ServerHttpRequest;

public interface TradeNotifyAdapter extends TradeAdapter{

	Object notify(String tradeMethod, String tradeStatus, ServerHttpRequest request,
			TradeStatusDispatcher dispatcher) throws TradeException;
}
