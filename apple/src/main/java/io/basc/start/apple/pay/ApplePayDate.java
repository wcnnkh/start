package io.basc.start.apple.pay;

import io.basc.framework.json.JsonObject;

public class ApplePayDate {
	private JsonObject jsonObject;
	private String name;

	public ApplePayDate(JsonObject jsonObject, String name) {
		this.jsonObject = jsonObject;
		this.name = name;
	}

	/**
	 * 日期时间格式类似于ISO8601
	 * 
	 * @return 日期时间
	 */
	public String getDate() {
		return jsonObject.getAsString(name);
	}

	/**
	 * UNIX纪元时间格式，以毫秒为单位, 使用此时间格式来处理日期
	 * 
	 * @return UNIX纪元时间格式
	 */
	public long getMs() {
		return jsonObject.getAsLong(name + "_ms");
	}

	/**
	 * 位于太平洋时区
	 * 
	 * @return 位于太平洋时区
	 */
	public String getPst() {
		return jsonObject.getAsString(name + "_pst");
	}
}
