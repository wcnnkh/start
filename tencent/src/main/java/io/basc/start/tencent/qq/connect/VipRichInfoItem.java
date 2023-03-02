package io.basc.start.tencent.qq.connect;

import io.basc.framework.json.JsonObject;
import io.basc.framework.json.JsonObjectWrapper;

public class VipRichInfoItem extends JsonObjectWrapper {
	private final String prefix;

	public VipRichInfoItem(JsonObject target, String prefix) {
		super(target);
		this.prefix = prefix;
	}

	/**
	 * 最后一次充值时间
	 * 
	 * @return 最后一次充值时间
	 */
	public long getStart() {
		return getAsLong(prefix + "_start");
	}

	/**
	 * 会员期限
	 * 
	 * @return 会员期限
	 */
	public long getEnd() {
		return getAsLong(prefix + "_end");
	}

	/**
	 * 充值方式
	 * 
	 * @return 充值方式
	 */
	public int getPayway() {
		return getAsInt(prefix + "_payway");
	}
}
