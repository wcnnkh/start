package io.basc.start.app.address.model;

import java.util.List;

import io.basc.start.app.address.pojo.Address;
import io.basc.start.app.address.pojo.UserAddress;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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
}
