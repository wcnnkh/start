package io.basc.start.kdniao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.basc.framework.json.JsonArray;
import io.basc.framework.json.JsonObject;

/**
 * 快递鸟物流轨迹
 * 
 * @author shuchaowen
 *
 */
public class Traces implements Serializable {
	private static final long serialVersionUID = 1L;
	private String acceptTime;// 时间
	private String acceptStation;// 描述
	private String remark;// 备注

	// 用于序列化
	Traces() {
	};

	public Traces(String acceptTime, String acceptStation, String remark) {
		this.acceptStation = acceptStation;
		this.acceptTime = acceptTime;
		this.remark = remark;
	}

	public Traces(JsonObject json) {
		this(json, true);
	}

	public Traces(JsonObject json, boolean serverJson) {
		this(json.getAsString(serverJson ? "AcceptTime" : "acceptTime"),
				json.getAsString(serverJson ? "AcceptStation" : "acceptStation"),
				json.getAsString(serverJson ? "Remark" : "remark"));
	}

	public final String getAcceptTime() {
		return acceptTime;
	}

	public final String getAcceptStation() {
		return acceptStation;
	}

	public final String getRemark() {
		return remark;
	}

	public static List<Traces> parseTraces(JsonArray jsonArray) {
		return parseTraces(jsonArray, true);
	}

	public static List<Traces> parseTraces(JsonArray jsonArray, boolean serverJson) {
		if (jsonArray == null) {
			return null;
		}

		List<Traces> list = new ArrayList<Traces>(jsonArray.size());
		for (int i = 0, size = jsonArray.size(); i < size; i++) {
			JsonObject jsonObject = jsonArray.getJsonObject(i);
			if (jsonObject == null) {
				continue;
			}

			list.add(new Traces(jsonObject, serverJson));
		}
		return list;
	}

	protected void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}

	protected void setAcceptStation(String acceptStation) {
		this.acceptStation = acceptStation;
	}

	protected void setRemark(String remark) {
		this.remark = remark;
	}
}
