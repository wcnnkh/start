package io.basc.integration.bytedance.poi;

import io.basc.integration.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class PoiBaseQueryAmapResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "POI地址")
	private String address;
	@Schema(description = "POI所在城市")
	private String city;
	@Schema(description = "经度")
	private String longitude;
	@Schema(description = "纬度")
	private String latitude;
	@Schema(description = "抖音POI ID")
	private String poi_id;
	@Schema(description = "高德POI ID")
	private String amap_id;
	@Schema(description = "POI名称")
	private String poi_name;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	public String getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(String poi_id) {
		this.poi_id = poi_id;
	}

	public String getAmap_id() {
		return amap_id;
	}

	public void setAmap_id(String amap_id) {
		this.amap_id = amap_id;
	}

	public String getPoi_name() {
		return poi_name;
	}

	public void setPoi_name(String poi_name) {
		this.poi_name = poi_name;
	}
}
