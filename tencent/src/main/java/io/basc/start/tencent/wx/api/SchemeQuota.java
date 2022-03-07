package io.basc.start.tencent.wx.api;

import io.basc.framework.json.JsonObject;
import lombok.Data;

@Data
public class SchemeQuota {
	private Long longTimeUsed;
	private Long longTimeLimit;

	public SchemeQuota() {
	}

	public SchemeQuota(JsonObject json) {
		this.longTimeLimit = json.getLong("long_time_limit");
		this.longTimeUsed = json.getLong("long_time_used");
	}
}
