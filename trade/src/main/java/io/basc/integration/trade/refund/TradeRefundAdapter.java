package io.basc.integration.trade.refund;

import io.basc.integration.trade.TradeAdapter;
import io.basc.integration.trade.TradeException;

public interface TradeRefundAdapter extends TradeAdapter{
	boolean refund(TradeRefund request) throws TradeException;
}
