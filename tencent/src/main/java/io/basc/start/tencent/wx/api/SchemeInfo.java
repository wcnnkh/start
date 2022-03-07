package io.basc.start.tencent.wx.api;

import io.basc.framework.json.JsonObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SchemeInfo extends JumpWxa {
	private String appid;
	private Long createTime;
	private Long expireTime;

	public SchemeInfo() {
	}

	public SchemeInfo(JsonObject json) {
		super(json);
		this.appid = json.getString("appid");
		this.createTime = json.getLong("create_time");
		this.expireTime = json.getLong("expire_time");
	}
}
