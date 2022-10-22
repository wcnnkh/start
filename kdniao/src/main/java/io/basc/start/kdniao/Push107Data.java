package io.basc.start.kdniao;

import io.basc.framework.json.JsonUtils;
import io.basc.framework.json.JsonArray;
import io.basc.framework.json.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Push107Data implements Serializable {
	private static final long serialVersionUID = 1L;
	private String businessId;
	private String pushTime;
	private int count;
	private List<Data> data;

	// 序列化
	Push107Data() {
	};

	public Push107Data(String jsonData) {
		if (jsonData == null) {
			return;
		}

		JsonObject jsonObject = JsonUtils.getJsonSupport().parseObject(jsonData);
		if (jsonObject == null) {
			return;
		}

		this.businessId = jsonObject.getAsString("EBusinessID");
		this.pushTime = jsonObject.getAsString("PushTime");
		this.count = jsonObject.getAsInt("Count");
		JsonArray array = jsonObject.getJsonArray("data");
		if (array != null) {
			List<Data> list = new ArrayList<Push107Data.Data>(array.size());
			for (int i = 0, len = array.size(); i < len; i++) {
				JsonObject json = array.getJsonObject(i);
				if (json == null) {
					continue;
				}

				list.add(new Data(json));
			}
			this.data = list;
		}
	}

	public String getBusinessId() {
		return businessId;
	}

	public String getPushTime() {
		return pushTime;
	}

	public int getCount() {
		return count;
	}

	public List<Data> getData() {
		return data;
	}

	public class Data extends KDNiaoResponse {
		private static final long serialVersionUID = 1L;
		// 订单编号
		private String orderCode;
		// 快递公司编码
		private String shipperCode;
		// 快递公司编码
		private String logisticCode;
		// 物流状态:0-无轨迹,1-已揽收, 2-在途中 201-到达派件城市，3-签收,4-问题件
		private String state;
		// 订阅接口的Bk值
		private String callBack;
		// 订单货款状态：1-待出款；2-已出款；3-已收款
		private String orderStatus;
		// 返款银行卡开户人（例：**伟、*佳）
		private String accountName;
		// 返款银行卡手机末四位
		private String accountTel;
		// 返款银行卡末四位
		private String accountNum;

		// 序列化
		Data() {
			super(null);
		}

		public Data(JsonObject json) {
			super(json);
			if (json == null) {
				return;
			}

			this.orderCode = json.getAsString("OrderCode");
			this.shipperCode = json.getAsString("ShipperCode");
			this.logisticCode = json.getAsString("LogisticCode");
			this.state = json.getAsString("State");
			this.callBack = json.getAsString("CallBack");
			this.orderStatus = json.getAsString("OrderState");
			this.accountName = json.getAsString("AccountName");
			this.accountTel = json.getAsString("AccountTel");
			this.accountNum = json.getAsString("AccountNum");
		}

		public String getOrderCode() {
			return orderCode;
		}

		public String getShipperCode() {
			return shipperCode;
		}

		public String getLogisticCode() {
			return logisticCode;
		}

		public String getState() {
			return state;
		}

		public String getCallBack() {
			return callBack;
		}

		public String getOrderStatus() {
			return orderStatus;
		}

		public String getAccountName() {
			return accountName;
		}

		public String getAccountTel() {
			return accountTel;
		}

		public String getAccountNum() {
			return accountNum;
		}
	}
}
