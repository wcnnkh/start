package io.basc.start.bytedance.poi;

import javax.validation.constraints.NotNull;

import io.basc.start.bytedance.oauth.ClientRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class PoiV2SpuStatusRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(required = true)
	@NotNull
	private String spu_ext_id;

	public String getSpu_ext_id() {
		return spu_ext_id;
	}

	public void setSpu_ext_id(String spu_ext_id) {
		this.spu_ext_id = spu_ext_id;
	}
}
