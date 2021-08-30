package io.basc.start.address.model;

import io.basc.framework.mapper.MapperUtils;
import io.basc.start.address.pojo.Address;

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
