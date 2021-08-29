package io.basc.app.address.model;

import io.basc.app.address.pojo.Address;
import io.basc.framework.mapper.MapperUtils;

import java.util.List;

public class AddressTree extends Address {
	private static final long serialVersionUID = 1L;
	private List<AddressTree> subList;

	public List<AddressTree> getSubList() {
		return subList;
	}

	public void setSubList(List<AddressTree> subList) {
		this.subList = subList;
	}
	
	@Override
	public String toString() {
		return MapperUtils.toString(this);
	}
}
