package scw.integration.bytedance.poi;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.oauth.ClientRequest;

public class PoiBaseQueryAmapRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "高德POI ID", required = true)
	@NotNull
	private String amap_id;

	public String getAmap_id() {
		return amap_id;
	}

	public void setAmap_id(String amap_id) {
		this.amap_id = amap_id;
	}
}
