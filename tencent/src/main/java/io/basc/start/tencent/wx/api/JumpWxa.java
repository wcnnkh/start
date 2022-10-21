package io.basc.start.tencent.wx.api;

import java.io.Serializable;

import io.basc.framework.json.JsonObject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JumpWxa implements Serializable {
	private static final long serialVersionUID = 1L;

	private String path;
	private String query;
	private String envVersion;

	public JumpWxa(JsonObject json) {
		this.path = json.getAsString("path");
		this.query = json.getAsString("query");
		this.envVersion = json.getAsString("env_version");
	}
}
