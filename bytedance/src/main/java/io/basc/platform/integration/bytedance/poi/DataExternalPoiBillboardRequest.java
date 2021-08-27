package io.basc.platform.integration.bytedance.poi;

import io.basc.platform.integration.bytedance.oauth.ClientRequest;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotNull;

public class DataExternalPoiBillboardRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "10：近30日餐饮类POI的热度TOP500；20：近30日景点类POI的热度TOP500；30：近30日住宿类POI的热度TOP500", required = true)
	@NotNull
	private Long billboard_type;

	public Long getBillboard_type() {
		return billboard_type;
	}

	public void setBillboard_type(Long billboard_type) {
		this.billboard_type = billboard_type;
	}

}
