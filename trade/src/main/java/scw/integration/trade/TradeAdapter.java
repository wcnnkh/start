package scw.integration.trade;

import scw.util.Accept;

public interface TradeAdapter extends Accept<String> {
	boolean accept(String tradeMethod);
}
