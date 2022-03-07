package io.basc.start.tencent.wx.api;

import io.basc.framework.json.JsonObject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UrlLinkInfo extends SchemeInfo {
	private static final long serialVersionUID = 1L;
	private CloudBase cloudBase;

	public UrlLinkInfo(JsonObject json) {
		super(json);
		this.cloudBase = json.containsKey("cloud_base") ? new CloudBase(json.getJsonObject("cloud_base")) : null;
	}
}
