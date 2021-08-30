package io.basc.start.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

public class PoiV2SupplierMatch extends PoiV2Supplier {
	private static final long serialVersionUID = 1L;
	@Schema(description = "高德POI ID")
	private String amap_id;
	@Schema(description = "经度")
	private String longitude;
	@Schema(description = "纬度")
	private String latitude;

	public String getAmap_id() {
		return amap_id;
	}

	public void setAmap_id(String amap_id) {
		this.amap_id = amap_id;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
