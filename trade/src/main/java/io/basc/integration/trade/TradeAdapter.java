package io.basc.integration.trade;

import io.basc.framework.util.Accept;

public interface TradeAdapter extends Accept<String> {
	boolean accept(String tradeMethod);
}
