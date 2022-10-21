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
		this.env = json.getAsString("evn");
		this.domain = json.getAsString("domain");
		this.path = json.getAsString("path");
		this.query = json.getAsString("query");
		this.resourceAppid = json.getAsString("resource_appid");
	}
}
