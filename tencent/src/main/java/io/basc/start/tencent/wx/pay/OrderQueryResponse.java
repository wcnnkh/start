package io.basc.start.tencent.wx.pay;

import io.basc.framework.json.JsonObject;

public class OrderQueryResponse extends WeiXinPayResponse {

	public OrderQueryResponse(JsonObject target) {
		super(target);
	}

	/**
	 * 用户标识
	 * 
	 * @return
	 */
	public String getOpenid() {
		return getAsString("openid");
	}

	/**
	 * 是否关注公众账号
	 * 
	 * @return
	 */
	public boolean isSubscribe() {
		return getAsBoolean("is_subscribe");
	}

	/**
	 * 交易类型
	 * 
	 * @return
	 */
	public String getTradeType() {
		return getAsString("trade_type");
	}

	/**
	 * 交易状态
	 * 
	 * @return
	 */
	public TradeState getTradeState() {
		return (TradeState) getAsEnum("trade_state", TradeState.class);
	}

	public String getRawTradeState() {
		return getAsString("trade_state");
	}

	/**
	 * 付款银行, 银行类型，采用字符串类型的银行标识
	 * 
	 * @return
	 */
	public String getBankType() {
		return getAsString("bank_type");
	}

	/**
	 * 订单总金额，单位为分
	 * 
	 * @return
	 */
	public int getTotalFee() {
		return getAsInt("total_fee");
	}

	/**
	 * 现金支付金额
	 * 
	 * @return
	 */
	public int getCashFee() {
		return getAsInt("cash_fee");
	}

	/**
	 * 微信支付订单号
	 * 
	 * @return
	 */
	public String getTransactionId() {
		return getAsString("transaction_id");
	}

	/**
	 * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
	 * 
	 * @return
	 */
	public String getOutTradeNo() {
		return getAsString("out_trade_no");
	}

	/**
	 * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。
	 * 
	 * @return
	 */
	public String getTimeEnd() {
		return getAsString("time_end");
	}

	/**
	 * 交易状态描述
	 * 
	 * @return
	 */
	public String getTradeStateDesc() {
		return getAsString("trade_state_desc");
	}
}
