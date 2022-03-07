package io.basc.start.tencent.wx.api;

import io.basc.framework.json.JsonObject;
import lombok.Data;

@Data
public class JumpWxa {
	private String path;
	private String query;
	private String envVersion;

	public JumpWxa() {
	}

	public JumpWxa(JsonObject json) {
		this.path = json.getString("path");
		this.query = json.getString("query");
		this.envVersion = json.getString("env_version");
	}
}
