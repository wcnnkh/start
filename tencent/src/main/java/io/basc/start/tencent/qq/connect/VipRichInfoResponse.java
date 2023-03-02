package io.basc.start.tencent.qq.connect;

import io.basc.framework.json.JsonObject;

public class VipRichInfoResponse extends QQResponse {

	public VipRichInfoResponse(JsonObject target) {
		super(target);
	}

	public boolean isVip() {
		return getAsBoolean("is_qq_vip");
	}

	public VipRichInfoItem getVip() {
		return new VipRichInfoItem(this, "qq_vip");
	}

	public VipRichInfoItem getYearVip() {
		return new VipRichInfoItem(this, "qq_year_vip");
	}

	/**
	 * QQ钻皇
	 * 
	 * @return QQ钻皇
	 */
	public VipRichInfoItem getZuanHuang() {
		return new VipRichInfoItem(this, "qq_zuanhuang");
	}

	/**
	 * 豪华版QQ会员
	 * 
	 * @return QQ会员
	 */
	public VipRichInfoItem getHaoHua() {
		return new VipRichInfoItem(this, "qq_haohua");
	}

	public VipRichInfoItem getSvip() {
		return new VipRichInfoItem(this, "qq_svip");
	}

	/**
	 * 非会员历史充值时间，仅在用户是非会员时信息有效
	 * 
	 * @return 充值时间
	 */
	public long getHistoryPayTime() {
		return getAsLong("history_pay_time");
	}

	/**
	 * 非会员历史充值到期时间，仅在用户是非会员时信息有效
	 * 
	 * @return 充值到期时间
	 */
	public long getHistoryEndTime() {
		return getAsLong("history_end_time");
	}
}
