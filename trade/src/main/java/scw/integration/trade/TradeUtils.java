package scw.integration.trade;

import scw.core.utils.StringUtils;

public class TradeUtils {
	/**
	 * 把钱保留两位小数
	 * 
	 * @param price 单位:分
	 * @return
	 */
	public static String formatNothingToYuan(long price) {
		return StringUtils.formatNumberPrecision((double) price / 100, 2);
	}
}
