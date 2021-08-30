package io.basc.start.trade.create;

import io.basc.framework.beans.BeanFactory;
import io.basc.framework.lang.Nullable;
import io.basc.start.trade.TradeException;

import java.util.ArrayList;

/**
 * 订单创建处理器
 * @author shuchaowen
 *
 */
public class TradeCreateProcessor extends ArrayList<TradeCreateAdapter> implements TradeCreateAdapter{
	private static final long serialVersionUID = 1L;

	public TradeCreateProcessor(){
		super();
	}
	
	public TradeCreateProcessor(BeanFactory beanFactory){
		addAll(beanFactory.getServiceLoader(TradeCreateAdapter.class).toList());
	}
	
	@Nullable
	public TradeCreateAdapter getAdapter(String tradeMethod){
		for(TradeCreateAdapter adapter : this){
			if(adapter.accept(tradeMethod)){
				return adapter;
			}
		}
		return null;
	}
	
	@Override
	public boolean accept(String tradeMethod) {
		for(TradeCreateAdapter adapter : this){
			if(adapter.accept(tradeMethod)){
				return true;
			}
		}
		return false;
	}

	@Override
	public TradeCreateResponse create(TradeCreate tradeCreate)
			throws TradeException {
		for(TradeCreateAdapter adapter : this){
			if(adapter.accept(tradeCreate.getTradeMethod())){
				return adapter.create(tradeCreate);
			}
		}
		throw new TradeException("not supported create [" + tradeCreate + "]");
	}

}
