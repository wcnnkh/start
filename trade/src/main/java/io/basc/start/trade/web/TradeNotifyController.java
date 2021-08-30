package io.basc.start.trade.web;

import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.annotation.Controller;
import io.basc.framework.web.ServerHttpRequest;
import io.basc.start.trade.status.TradeStatusDispatcher;

@Controller(value = TradeNotifyConfig.CONTROLLER_PREFIX, methods = {
		HttpMethod.GET, HttpMethod.POST })
public class TradeNotifyController {
	private final TradeStatusDispatcher tradeStatusDispatcher;
	private final TradeNotifyProcessor tradeNotifyProcessor;

	public TradeNotifyController(TradeNotifyProcessor tradeNotifyProcessor,
			TradeStatusDispatcher tradeStatusDispatcher) {
		this.tradeNotifyProcessor = tradeNotifyProcessor;
		this.tradeStatusDispatcher = tradeStatusDispatcher;
	}

	@Controller(value = "/{tradeMethod}/{tradeStatus}")
	public Object notify(String tradeMethod, String tradeStatus, ServerHttpRequest request) {
		return tradeNotifyProcessor.notify(tradeMethod, tradeStatus, request, tradeStatusDispatcher);
	}
}
