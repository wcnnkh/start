package scw.integration.trade.web;

import scw.http.server.ServerHttpRequest;
import scw.integration.trade.TradeAdapter;
import scw.integration.trade.TradeException;
import scw.integration.trade.status.TradeStatusDispatcher;

public interface TradeNotifyAdapter extends TradeAdapter{

	Object notify(String tradeMethod, String tradeStatus, ServerHttpRequest request,
			TradeStatusDispatcher dispatcher) throws TradeException;
}
