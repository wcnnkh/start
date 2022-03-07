package io.basc.start.tencent.wx.api;

import lombok.EqualsAndHashCode;

import lombok.NoArgsConstructor;
import lombok.ToString;
import io.basc.framework.json.JsonObject;
import lombok.Data;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UrlLinkQuota extends SchemeQuota {
	private static final long serialVersionUID = 1L;

	public UrlLinkQuota(JsonObject json) {
		super(json);
	}
}
