package io.basc.start.tencent.wx.pay;

import io.basc.framework.json.JsonObject;

public class OrderQueryResponse extends WeiXinPayResponse {

	public OrderQueryResponse(JsonObject target) {
		super(target);
	}

	public String getOpenid() {
		return getAsString("openid");
	}

	public boolean isSubscribe() {
		return getAsBoolean("is_subscribe");
	}

	public String getTradeType() {
		return getAsString("trade_type");
	}

	public TradeState getTradeState() {
		return (TradeState) getAsEnum("trade_state", TradeState.class);
	}

	public String getRawTradeState() {
		return getAsString("trade_state");
	}

	public String getBankType() {
		return getAsString("bank_type");
	}

	public int getTotalFee() {
		return getAsInt("total_fee");
	}

	public int getCashFee() {
		return getAsInt("cash_fee");
	}

	public String getTransactionId() {
		return getAsString("transaction_id");
	}

	public String getOutTradeNo() {
		return getAsString("out_trade_no");
	}

	public String getTimeEnd() {
		return getAsString("time_end");
	}

	public String getTradeStateDesc() {
		return getAsString("trade_state_desc");
	}
}
