package io.basc.start.app.web;

import io.basc.framework.beans.annotation.Autowired;
import io.basc.framework.context.result.Result;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mapper.MapperUtils;
import io.basc.framework.security.session.UserSession;
import io.basc.framework.web.pattern.annotation.RequestMapping;
import io.basc.start.app.address.model.UserAddressInfo;
import io.basc.start.app.address.model.UserAddressModel;
import io.basc.start.app.address.pojo.Address;
import io.basc.start.app.address.pojo.UserAddress;
import io.basc.start.app.address.service.AddressService;
import io.basc.start.app.address.service.UserAddressService;
import io.basc.start.app.user.pojo.User;
import io.basc.start.app.user.security.LoginRequired;
import io.basc.start.app.user.service.UserService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping(value = "/user/address", methods = { HttpMethod.GET, HttpMethod.POST })
@LoginRequired
public class UserAddressController {
	private UserAddressService userAddressService;
	@Autowired
	private UserService userService;
	@Autowired
	private ResultFactory resultFactory;
	@Autowired
	private AddressService addressService;

	public UserAddressController(UserAddressService userAddressService) {
		this.userAddressService = userAddressService;
	}

	@RequestMapping(value = "info")
	public Result info(UserSession<Long> requestUser, long id) {
		UserAddress userAddress = userAddressService.getById(id);
		if (userAddress == null) {
			return resultFactory.error("用户地址不存在");
		}

		List<Address> list = addressService.getParents(userAddress.getAddressId(), true);
		StringBuilder sb = new StringBuilder();
		for (Address address : list) {
			sb.append(address.getName());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(MapperUtils.getFields(UserAddress.class).all().getValueMap(userAddress));
		map.put("address", sb.toString());
		return resultFactory.success(map);
	}

	@RequestMapping(value = "list")
	public Result list(UserSession<Long> requestUser) {
		User user = userService.getUser(requestUser.getUid());
		if (user == null) {
			return resultFactory.error("用户不存在");
		}

		List<UserAddressInfo> list = userAddressService.getUserAddressList(user.getUid());
		if (list == null) {
			list = Collections.emptyList();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("defaultAddressId", user.getDefaultAddressId());
		map.put("list", list);
		return resultFactory.success(map);
	}

	@RequestMapping(value = "create")
	public Result create(UserSession<Long> requestUser, UserAddressModel userAddressModel) {
		return userAddressService.create(requestUser.getUid(), userAddressModel);
	}

	@RequestMapping(value = "update")
	public Result update(long id, UserSession<Long> requestUser, UserAddressModel userAddressModel) {
		UserAddress userAddress = userAddressService.getById(id);
		if (userAddress == null) {
			return resultFactory.error("地址不存在");
		}

		if (userAddress.getUid() != requestUser.getUid()) {
			return resultFactory.error("非法请求，只能操作自己的地址");
		}

		return userAddressService.update(id, userAddressModel);
	}

	@RequestMapping(value = "update_default_id")
	public Result updateDefaultAddressId(UserSession<Long> requestUser, long id) {
		UserAddress userAddress = userAddressService.getById(id);
		if (userAddress == null) {
			return resultFactory.error("地址不存在");
		}

		if (userAddress.getUid() != requestUser.getUid()) {
			return resultFactory.error("非法请求，只能操作自己的地址");
		}

		return userService.updateDefaultAddressId(requestUser.getUid(), id);
	}
}
