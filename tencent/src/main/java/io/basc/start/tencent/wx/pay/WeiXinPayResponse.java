package io.basc.start.tencent.wx.pay;

import io.basc.framework.json.JsonObject;
import io.basc.framework.json.JsonObjectWrapper;

public class WeiXinPayResponse extends JsonObjectWrapper {
	private static final String SUCCESS_TEXT = "SUCCESS";

	public WeiXinPayResponse(JsonObject target) {
		super(target);
	}

	public boolean isReturnSuccess() {
		return containsKey("return_code") && SUCCESS_TEXT.equals(getAsString("return_code"));
	}

	public String getReturnMsg() {
		return getAsString("return_msg");
	}

	public boolean isResultSuccess() {
		return containsKey("result_code") && SUCCESS_TEXT.equals(getAsString("result_code"));
	}

	public String getResultErrCodeDes() {
		return getAsString("err_code_des");
	}

	public String getResultErrCode() {
		return getAsString("err_code");
	}

	public boolean isSuccess() {
		return isReturnSuccess() && isResultSuccess();
	}

	public String getAppId() {
		return getAsString("appid");
	}

	public String getMchId() {
		return getAsString("mch_id");
	}

	/**
	 * 微信支付分配的终端设备号，
	 * 
	 * @return 终端设备号
	 */
	public String getDeviceInfo() {
		return getAsString("device_info");
	}

	public String getNonceStr() {
		return getAsString("nonce_str");
	}

	public String getSign() {
		return getAsString("sign");
	}
}
