package io.basc.start.tencent.wx.pay;

import io.basc.framework.json.JsonObject;

/**
 * {@link https://pay.weixin.qq.com/wiki/doc/api/tools/cash_coupon.php?chapter=13_4&index=3}
 * @author shuchaowen
 *
 */
public class SendredpackResponse extends WeiXinPayResponse{

	public SendredpackResponse(JsonObject target) {
		super(target);
	}

	public String getMchBillno(){
		return getAsString("mch_billno");
	}
	
	public String getWxappid(){
		return getAsString("wxappid");
	}
	
	public String getReOpenid(){
		return getAsString("re_openid");
	}
	
	public int getTotalAmount(){
		return getAsInt("total_amount");
	}
	
	public String getSendListid(){
		return getAsString("send_listid");
	}
}
