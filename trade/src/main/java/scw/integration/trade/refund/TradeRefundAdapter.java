package scw.integration.trade.refund;

import scw.integration.trade.TradeAdapter;
import scw.integration.trade.TradeException;

public interface TradeRefundAdapter extends TradeAdapter{
	boolean refund(TradeRefund request) throws TradeException;
}
