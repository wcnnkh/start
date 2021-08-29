package io.basc.integration.trade.create;

import io.basc.integration.trade.TradeAdapter;
import io.basc.integration.trade.TradeException;

public interface TradeCreateAdapter extends TradeAdapter{
	TradeCreateResponse create(TradeCreate request) throws TradeException;
}
