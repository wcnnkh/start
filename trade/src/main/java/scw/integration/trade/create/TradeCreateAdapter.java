package scw.integration.trade.create;

import scw.integration.trade.TradeAdapter;
import scw.integration.trade.TradeException;

public interface TradeCreateAdapter extends TradeAdapter{
	TradeCreateResponse create(TradeCreate request) throws TradeException;
}
