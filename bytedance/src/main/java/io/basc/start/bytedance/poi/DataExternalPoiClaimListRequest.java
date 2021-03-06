package io.basc.start.bytedance.poi;

import javax.validation.constraints.NotNull;

import io.basc.start.bytedance.oauth.ClientPagingRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalPoiClaimListRequest extends ClientPagingRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "通过/oauth/access_token/获取，用户唯一标志", example = "ba253642-0590-40bc-9bdf-9a1334b94059", required = true)
	@NotNull
	private String open_id;

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
}
