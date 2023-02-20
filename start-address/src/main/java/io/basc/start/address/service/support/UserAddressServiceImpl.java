package io.basc.start.address.service.support;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.basc.framework.context.annotation.Service;
import io.basc.framework.context.ioc.annotation.Autowired;
import io.basc.framework.context.transaction.DataResult;
import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.db.DB;
import io.basc.framework.mapper.Copy;
import io.basc.framework.util.CollectionUtils;
import io.basc.framework.util.StringUtils;
import io.basc.start.address.model.UserAddressInfo;
import io.basc.start.address.model.UserAddressModel;
import io.basc.start.address.pojo.Address;
import io.basc.start.address.pojo.UserAddress;
import io.basc.start.address.service.AddressService;
import io.basc.start.address.service.UserAddressService;
import io.basc.start.template.service.impl.TemplateServiceSupport;

@Service
public class UserAddressServiceImpl extends TemplateServiceSupport implements UserAddressService {
	@Autowired
	private AddressService addressService;

	public UserAddressServiceImpl(DB db, ResultFactory resultFactory) {
		super(db, resultFactory);
		db.createTable(UserAddress.class, false);
	}

	private UserAddressInfo wrapper(UserAddress userAddress) {
		UserAddressInfo info = new UserAddressInfo();
		Copy.copy(userAddress, info);
		info.setAddresses(addressService.getParents(userAddress.getAddressId(), true));
		return info;
	}

	public UserAddressInfo getUserAddressInfo(long id) {
		UserAddress userAddress = db.getById(UserAddress.class, id);
		if (userAddress == null) {
			return null;
		}

		return wrapper(userAddress);
	}

	public List<UserAddressInfo> getUserAddressList(long uid) {
		List<UserAddress> userAddressList = db
				.query(UserAddress.class, "select * from user_address where uid=? order by lastUpdateTime desc", uid)
				.toList();
		if (CollectionUtils.isEmpty(userAddressList)) {
			return Collections.emptyList();
		}

		List<UserAddressInfo> list = new ArrayList<UserAddressInfo>();
		for (UserAddressInfo info : list) {
			list.add(wrapper(info));
		}
		return list;
	}

	public DataResult<UserAddressInfo> create(long uid, UserAddressModel userAddressModel) {
		if (StringUtils.isAnyEmpty(userAddressModel.getDetailedAddress(), userAddressModel.getContactName(),
				userAddressModel.getContactPhone())) {
			return resultFactory.parameterError();
		}

		Address address = addressService.getById(userAddressModel.getAddressId());
		if (address == null) {
			return resultFactory.error("地址不存在(" + userAddressModel.getAddressId() + ")");
		}

		UserAddress userAddress = new UserAddress();
		Copy.copy(userAddressModel, userAddress);
		userAddress.setUid(uid);
		userAddress.setCreateTime(System.currentTimeMillis());
		userAddress.setLastUpdateTime(userAddress.getCreateTime());
		db.save(userAddress);
		return resultFactory.success(wrapper(userAddress));
	}

	public DataResult<UserAddressInfo> update(long id, UserAddressModel userAddressModel) {
		if (StringUtils.isAnyEmpty(userAddressModel.getContactName(), userAddressModel.getContactPhone(),
				userAddressModel.getContactName())) {
			return resultFactory.parameterError();
		}

		Address address = addressService.getById(userAddressModel.getAddressId());
		if (address == null) {
			return resultFactory.error("地址不存在(" + userAddressModel.getAddressId() + ")");
		}

		UserAddress userAddress = getById(id);
		if (userAddress == null) {
			return resultFactory.error("用户地址不存在(" + id + ")");
		}

		Copy.copy(userAddressModel, userAddress);
		userAddress.setLastUpdateTime(System.currentTimeMillis());
		db.update(userAddress);
		return resultFactory.success(wrapper(userAddress));
	}

	public UserAddress getById(long id) {
		return db.getById(UserAddress.class, id);
	}

}
