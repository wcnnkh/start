package io.basc.start.address.model;

import io.basc.framework.mapper.MapperUtils;
import io.basc.start.address.pojo.Address;
import io.basc.start.address.pojo.UserAddress;

import java.util.List;

public class UserAddressInfo extends UserAddress {
	private static final long serialVersionUID = 1L;
	private List<Address> addresses;

	public String getAddress() {
		StringBuilder sb = new StringBuilder();
		for (Address address : addresses) {
			sb.append(address.getName());
		}
		return sb.toString();
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}
	
	@Override
	public String toString() {
		return MapperUtils.toString(this);
	}
}
