package io.basc.start.tencent.qq.connect;

import io.basc.framework.json.JsonObject;

public class VipInfoResponse extends QQResponse {

	public VipInfoResponse(JsonObject target) {
		super(target);
	}

	/**
	 * 标识是否QQ会员
	 * 
	 * @return
	 */
	public boolean isVip() {
		return getAsBoolean("is_qq_vip");
	}

	/**
	 * 标识是否为年费QQ会员
	 * 
	 * @return
	 */
	public boolean isYearVip() {
		return getAsBoolean("is_qq_year_vip");
	}

	/**
	 * QQ会员等级信息
	 * 
	 * @return
	 */
	public int getVipLevel() {
		return getAsInt("qq_vip_level");
	}
}
