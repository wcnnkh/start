package io.basc.start.trade.refund;

import io.basc.start.trade.TradeAdapter;
import io.basc.start.trade.TradeException;

public interface TradeRefundAdapter extends TradeAdapter{
	boolean refund(TradeRefund request) throws TradeException;
}
