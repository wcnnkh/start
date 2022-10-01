package io.basc.start.trade;

import java.util.function.Predicate;

public interface TradeAdapter extends Predicate<String> {
	boolean test(String tradeMethod);
}
