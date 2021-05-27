package scw.integration.trade.web;

import scw.http.HttpMethod;
import scw.integration.trade.status.TradeStatusDispatcher;
import scw.mvc.annotation.Controller;
import scw.web.ServerHttpRequest;

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
