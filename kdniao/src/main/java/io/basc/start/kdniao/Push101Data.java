package io.basc.start.kdniao;

import io.basc.framework.json.JsonUtils;
import io.basc.framework.json.JsonArray;
import io.basc.framework.json.JsonObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Push101Data implements Serializable {
	private static final long serialVersionUID = 1L;
	private String businessId;
	private String pushTime;
	private int count;
	private List<Data> data;

	// 序列化
	Push101Data() {
	};

	public Push101Data(String jsonData) {
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
			List<Data> list = new ArrayList<Push101Data.Data>(array.size());
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

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getPushTime() {
		return pushTime;
	}

	public void setPushTime(String pushTime) {
		this.pushTime = pushTime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public static class Data extends KDNiaoResponse {
		private static final long serialVersionUID = 1L;
		private String shipperCode;
		// 快递公司编码
		private String logisticCode;
		// 物流状态: 0-无轨迹，1-已揽收，2-在途中，3-签收,4-问题件
		private String state;
		// 订阅接口的Bk值
		private String callBack;
		// 预计到达时间yyyy-mm-dd
		private String stimatedDeliveryTime;
		private List<Traces> traces;

		// 序列化
		Data() {
			super(null);
		}

		public Data(JsonObject json) {
			super(json);
			this.shipperCode = json.getAsString("ShipperCode");
			this.logisticCode = json.getAsString("LogisticCode");
			this.state = json.getAsString("State");
			this.callBack = json.getAsString("CallBack");
			this.traces = Traces.parseTraces(json.getJsonArray("Traces"));
			this.stimatedDeliveryTime = json.getAsString("EstimatedDeliveryTime");
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

		public String getStimatedDeliveryTime() {
			return stimatedDeliveryTime;
		}

		public List<Traces> getTraces() {
			return traces;
		}
	}
}
