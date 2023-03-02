package io.basc.start.tencent.wx.pay;

import java.util.Collections;
import java.util.List;

import io.basc.framework.json.JsonArray;
import io.basc.framework.json.JsonObject;
import io.basc.framework.json.JsonObjectWrapper;

public class GethbinfoResponse extends WeiXinPayResponse {

	public GethbinfoResponse(JsonObject target) {
		super(target);
	}

	/**
	 * 商户使用查询API填写的商户单号的原路返回
	 * 
	 * @return 商户单号
	 */
	public String getMchBillno() {
		return getAsString("mch_billno");
	}

	/**
	 * 使用API发放现金红包时返回的红包单号
	 * 
	 * @return 红包单号
	 */
	public String getDetailId() {
		return getAsString("detail_id");
	}

	public HbStatus getStatus() {
		return (HbStatus) getAsEnum("status", HbStatus.class);
	}

	public SendType getSendType() {
		return (SendType) getAsEnum("send_type", SendType.class);
	}

	public HbType getHbType() {
		return (HbType) getAsEnum("hb_type", HbType.class);
	}

	public int getTotalNum() {
		return getAsInt("total_num");
	}

	/**
	 * 红包总金额
	 * 
	 * @return 单位分
	 */
	public int getTotalAmount() {
		return getAsInt("total_amount");
	}

	/**
	 * 发送失败原因
	 * 
	 * @return 失败原因
	 */
	public String getReason() {
		return getAsString("reason");
	}

	/**
	 * 红包发送时间 2015-04-21 20:00:00
	 * 
	 * @return 发送时间
	 */
	public String getSendTime() {
		return getAsString("send_time");
	}

	/**
	 * 红包的退款时间（如果其未领取的退款）
	 * 
	 * @return 退款时间
	 */
	public String getRefundTime() {
		return getAsString("refund_time");
	}

	/**
	 * 红包退款金额
	 * 
	 * @return 退款金额
	 */
	public int getRefundAmount() {
		return getAsInt("refund_amount");
	}

	public String getWishing() {
		return getAsString("wishing");
	}

	public String getremark() {
		return getAsString("remark");
	}

	/**
	 * 活动名称
	 * 
	 * @return 活动名称
	 */
	public String getActName() {
		return getAsString("act_name");
	}

	/**
	 * 裂变红包的领取列表
	 * 
	 * @return 裂变红包的领取列表
	 */
	public List<HbInfo> getHbList() {
		JsonArray jsonArray = getJsonArray("hblist");
		if (jsonArray == null) {
			return Collections.emptyList();
		}
		return jsonArray.convert(HbInfo.class);
	}

	public static class HbInfo extends JsonObjectWrapper {

		public HbInfo(JsonObject target) {
			super(target);
		}

		public String getOpenid() {
			return getAsString("openid");
		}

		public int getAmount() {
			return getAsInt("amount");
		}

		/**
		 * 领取红包的时间 2015-04-21 20:00:00
		 * 
		 * @return 领取红包的时间
		 */
		public String getRcvTime() {
			return getAsString("rcv_time");
		}
	}
}
