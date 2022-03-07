package io.basc.start.tencent.wx.api;

import java.io.Serializable;

import io.basc.framework.json.JsonObject;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CloudBase implements Serializable{
	private static final long serialVersionUID = 1L;
	private String env;
	private String domain;
	private String path;
	private String query;
	private String resourceAppid;

	public CloudBase(JsonObject json) {
		this.env = json.getString("evn");
		this.domain = json.getString("domain");
		this.path = json.getString("path");
		this.query = json.getString("query");
		this.resourceAppid = json.getString("resource_appid");
	}
}
