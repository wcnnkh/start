package io.basc.start.address.model;

import io.basc.framework.lang.Description;
import io.basc.framework.mapper.MapperUtils;

import java.io.Serializable;

public class AddressModel implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private String name;
	@Description("经度")
	private float longitude;
	@Description("纬度")
	private float latitude;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	@Override
	public String toString() {
		return MapperUtils.toString(this);
	}
}
