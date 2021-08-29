package io.basc.app.address.service;

import io.basc.app.address.model.UserAddressInfo;
import io.basc.app.address.model.UserAddressModel;
import io.basc.app.address.pojo.UserAddress;
import io.basc.framework.context.result.DataResult;

import java.util.List;

/**
 * 用户地址管理
 * @author shuchaowen
 *
 */
public interface UserAddressService {
	UserAddress getById(long id);
	
	UserAddressInfo getUserAddressInfo(long id);

	/**
	 * 获取用户地址列表
	 * @param uid
	 * @return
	 */
	List<UserAddressInfo> getUserAddressList(long uid);

	/**
	 * 创建一个用户地址
	 * @param uid
	 * @param userAddressModel
	 * @return
	 */
	DataResult<UserAddressInfo> create(long uid, UserAddressModel userAddressModel);

	/**
	 * 此方法未检验uid
	 * @param id
	 * @param userAddressModel
	 * @return
	 */
	DataResult<UserAddressInfo> update(long id, UserAddressModel userAddressModel);
}
