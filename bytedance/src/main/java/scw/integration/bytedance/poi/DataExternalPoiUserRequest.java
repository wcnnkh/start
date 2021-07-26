package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.oauth.ClientRequest;

public class DataExternalPoiUserRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "抖音poi_id")
	private String poi_id;
	@Schema(description = "近7/15/30天")
	private Long date_type;

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
}
