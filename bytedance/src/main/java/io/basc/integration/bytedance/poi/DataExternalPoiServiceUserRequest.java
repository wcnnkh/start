package io.basc.integration.bytedance.poi;

import javax.validation.constraints.NotNull;

import io.basc.integration.bytedance.oauth.ClientRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalPoiServiceUserRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "抖音poi_id", required = true)
	@NotNull
	private String poi_id;
	@Schema(description = "近7/15/30天", required = true)
	@NotNull
	private Long date_type;
	@Schema(description = "服务类型，40:民宿")
	private Long service_type;

	public String getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(String poi_id) {
		this.poi_id = poi_id;
	}

	public Long getDate_type() {
		return date_type;
	}

	public void setDate_type(Long date_type) {
		this.date_type = date_type;
	}

	public Long getService_type() {
		return service_type;
	}

	public void setService_type(Long service_type) {
		this.service_type = service_type;
	}

}
