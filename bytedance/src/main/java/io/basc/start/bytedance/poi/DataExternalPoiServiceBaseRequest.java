package io.basc.start.bytedance.poi;

import javax.validation.constraints.NotNull;

import io.basc.start.bytedance.oauth.ClientRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalPoiServiceBaseRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "抖音poi_id", required = true)
	@NotNull
	private String poi_id;
	@Schema(description = "服务类型，40:民宿")
	private Long service_type;
	@Schema(description = "最近30天，开始日期(yyyy-MM-dd)", required = true)
	@NotNull
	private String begin_date;
	@Schema(description = "最近30天，结束日期(yyyy-MM-dd)", required = true)
	@NotNull
	private String end_date;

	public String getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(String poi_id) {
		this.poi_id = poi_id;
	}

	public Long getService_type() {
		return service_type;
	}

	public void setService_type(Long service_type) {
		this.service_type = service_type;
	}

	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
}
