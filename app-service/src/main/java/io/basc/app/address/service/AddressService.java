package io.basc.app.address.service;

import io.basc.app.address.model.AddressTree;
import io.basc.app.address.pojo.Address;

import java.util.List;

public interface AddressService {
	Address getById(int id);

	/**
	 * 获取所有父级地址
	 * 
	 * @param id
	 * @param includeSelf
	 *            是否包含自身
	 * @return
	 */
	List<Address> getParents(int id, boolean includeSelf);

	List<Address> getAddressSubList(int id);

	List<Address> getRootAddressList();
	
	/**
	 * 获取全部的地址<br/>
	 * 数据较大，可配合lastModified实现缓存
	 * @return
	 */
	List<Address> getAddressList();
	
	/**
	 * 获取全部的地址并组装成树结构<br/>
	 * 数据较大，可配合lastModified实现缓存
	 * @return
	 */
	List<AddressTree> getAddressTrees();
	
	/**
	 * 最后一次修改时间
	 * @return
	 */
	long lastModified();
}
