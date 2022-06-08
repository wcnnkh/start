package io.basc.start.app.address.model;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户地址模版
 * 
 * @author shuchaowen
 *
 */
@Data
public class UserAddressModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int addressId;
	private String detailedAddress;// 详细地址
	private String contactPhone;// 联系人手机号
	private String contactName;// 联系人名称
}
