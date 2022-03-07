package io.basc.start.tencent.wx.api;

import java.io.Serializable;

import io.basc.framework.json.JsonObject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SchemeQuota implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long longTimeUsed;
	private Long longTimeLimit;

	public SchemeQuota(JsonObject json) {
		this.longTimeLimit = json.getLong("long_time_limit");
		this.longTimeUsed = json.getLong("long_time_used");
	}
}
