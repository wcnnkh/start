package io.basc.start.trade.refund;

import io.basc.start.trade.Trade;
import io.basc.start.trade.TradeUtils;

public class TradeRefund extends Trade{
	private static final long serialVersionUID = 1L;
	/**
	 * 原订单总金额
	 */
	private int totalTradeAmount;
	
	private String thirdpartyTradeNo;

	public int getTotalTradeAmount() {
		return totalTradeAmount;
	}

	public void setTotalTradeAmount(int totalTradeAmount) {
		this.totalTradeAmount = totalTradeAmount;
	}

	public String getTotalTradeAmountDescribe(){
		return TradeUtils.formatNothingToYuan(totalTradeAmount);
	}

	public String getThirdpartyTradeNo() {
		return thirdpartyTradeNo;
	}

	public void setThirdpartyTradeNo(String thirdpartyTradeNo) {
		this.thirdpartyTradeNo = thirdpartyTradeNo;
	}
}
