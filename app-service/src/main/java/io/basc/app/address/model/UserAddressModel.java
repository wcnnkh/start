package io.basc.app.address.model;

import io.basc.framework.mapper.MapperUtils;

import java.io.Serializable;

/**
 * 用户地址模版
 * 
 * @author shuchaowen
 *
 */
public class UserAddressModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int addressId;
	private String detailedAddress;// 详细地址
	private String contactPhone;// 联系人手机号
	private String contactName;// 联系人名称

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getDetailedAddress() {
		return detailedAddress;
	}

	public void setDetailedAddress(String detailedAddress) {
		this.detailedAddress = detailedAddress;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	@Override
	public String toString() {
		return MapperUtils.toString(this);
	}
}
