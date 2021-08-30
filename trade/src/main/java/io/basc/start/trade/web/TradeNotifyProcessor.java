package io.basc.start.trade.web;

import io.basc.framework.beans.BeanFactory;
import io.basc.framework.lang.Nullable;
import io.basc.framework.web.ServerHttpRequest;
import io.basc.start.trade.TradeException;
import io.basc.start.trade.status.TradeStatusDispatcher;

import java.util.ArrayList;

public class TradeNotifyProcessor extends ArrayList<TradeNotifyAdapter> implements TradeNotifyAdapter{
	private static final long serialVersionUID = 1L;

	public TradeNotifyProcessor(){
		super();
	}
	
	public TradeNotifyProcessor(BeanFactory beanFactory){
		addAll(beanFactory.getServiceLoader(TradeNotifyAdapter.class).toList());
	}
	
	@Nullable
	public TradeNotifyAdapter getAdapter(String method, String status){
		for(TradeNotifyAdapter adapter : this){
			if(adapter.accept(method)){
				return adapter;
			}
		}
		return null;
	}
	
	@Override
	public boolean accept(String tradeMethod) {
		for(TradeNotifyAdapter adapter : this){
			if(adapter.accept(tradeMethod)){
				return true;
			}
		}
		return false;
	}

	@Override
	public Object notify(String tradeMethod, String tradeStatus,
			ServerHttpRequest request, TradeStatusDispatcher dispatcher)
			throws TradeException {
		for(TradeNotifyAdapter adapter : this){
			if(adapter.accept(tradeMethod)){
				return adapter.notify(tradeMethod, tradeStatus, request, dispatcher);
			}
		}
		throw new TradeException("not supported notify tradeMethod [" + tradeMethod + "] tradeStatus [" + tradeStatus + "] request [" + request + "]");
	}

}
