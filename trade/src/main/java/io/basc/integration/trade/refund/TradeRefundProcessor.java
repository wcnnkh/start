package io.basc.integration.trade.refund;

import io.basc.framework.beans.BeanFactory;
import io.basc.framework.lang.Nullable;
import io.basc.integration.trade.TradeException;

import java.util.ArrayList;

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
