package scw.integration.trade.refund;

import java.util.ArrayList;

import scw.beans.BeanFactory;
import scw.integration.trade.TradeException;
import scw.lang.Nullable;

/**
 * 退款处理器
 * @author shuchaowen
 *
 */
public class TradeRefundProcessor extends ArrayList<TradeRefundAdapter>
		implements TradeRefundAdapter {
	private static final long serialVersionUID = 1L;
	
	public TradeRefundProcessor() {
		super();
	}
	
	public TradeRefundProcessor(BeanFactory beanFactory){
		addAll(beanFactory.getServiceLoader(TradeRefundAdapter.class).toList());
	}

	@Nullable
	public TradeRefundAdapter getAdapter(String tradeMethod) {
		for (TradeRefundAdapter adapter : this) {
			if (adapter.accept(tradeMethod)) {
				return adapter;
			}
		}
		return null;
	}

	@Override
	public boolean accept(String tradeMethod) {
		for (TradeRefundAdapter adapter : this) {
			if (adapter.accept(tradeMethod)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean refund(TradeRefund tradeRefund) throws TradeException {
		for (TradeRefundAdapter adapter : this) {
			if (adapter.accept(tradeRefund.getTradeMethod())) {
				return adapter.refund(tradeRefund);
			}
		}
		throw new TradeException("not supported refund [" + tradeRefund + "]");
	}

}
