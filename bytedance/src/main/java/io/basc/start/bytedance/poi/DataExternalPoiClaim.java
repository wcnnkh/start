package io.basc.start.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class DataExternalPoiClaim implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "poi名称")
	private String name;
	@Schema(description = "poi的Id")
	private String poi_id;
	@Schema(description = "地址详情")
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(String poi_id) {
		this.poi_id = poi_id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
