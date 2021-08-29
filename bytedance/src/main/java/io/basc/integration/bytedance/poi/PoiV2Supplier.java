package io.basc.integration.bytedance.poi;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class PoiV2Supplier implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "POI所在省份")
	private String province;
	@Schema(description = "POI地址")
	private String address;
	@Schema(description = "抖音POI ID")
	private String poi_id;
	@Schema(description = "POI名称")
	private String poi_name;
	@Schema(description = "第三方商户ID")
	private String supplier_ext_id;
	@Schema(description = "POI所在城市")
	private String city;
	@Schema(description = "其他信息")
	private String extra;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(String poi_id) {
		this.poi_id = poi_id;
	}

	public String getPoi_name() {
		return poi_name;
	}

	public void setPoi_name(String poi_name) {
		this.poi_name = poi_name;
	}

	public String getSupplier_ext_id() {
		return supplier_ext_id;
	}

	public void setSupplier_ext_id(String supplier_ext_id) {
		this.supplier_ext_id = supplier_ext_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}
}
