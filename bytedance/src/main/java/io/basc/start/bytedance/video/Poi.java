package io.basc.start.bytedance.video;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class Poi implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "	国家编码", example = "CN")
	private String country_code;
	@Schema(description = "	唯一ID", example = "6601131771805304836")
	private String poi_id;
	@Schema(description = "区域名称", example = "东城区")
	private String district;
	@Schema(description = "经纬度，格式：X,Y", example = "116.401179,39.902768")
	private String location;
	@Schema(description = "名称", example = "北京市")
	private String poi_name;
	@Schema(description = "省份", example = "北京市")
	private String province;
	@Schema(description = "地址", example = "东城区")
	private String address;
	@Schema(description = "城市", example = "北京市")
	private String city;
	@Schema(description = "城市编码", example = "110101")
	private String city_code;
	@Schema(description = "国家", example = "中国")
	private String country;

	public String getCountry_code() {
		return country_code;
	}

	public void setCountry_code(String country_code) {
		this.country_code = country_code;
	}

	public String getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(String poi_id) {
		this.poi_id = poi_id;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPoi_name() {
		return poi_name;
	}

	public void setPoi_name(String poi_name) {
		this.poi_name = poi_name;
	}

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

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
