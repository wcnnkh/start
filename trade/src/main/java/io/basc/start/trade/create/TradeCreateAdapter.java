package io.basc.start.trade.create;

import io.basc.start.trade.TradeAdapter;
import io.basc.start.trade.TradeException;

public interface TradeCreateAdapter extends TradeAdapter{
	TradeCreateResponse create(TradeCreate request) throws TradeException;
}
