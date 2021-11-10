package io.basc.start.app.web;

import io.basc.framework.beans.annotation.Autowired;
import io.basc.framework.context.result.Result;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.web.ServerHttpRequest;
import io.basc.framework.web.ServerHttpResponse;
import io.basc.framework.web.WebUtils;
import io.basc.framework.web.pattern.annotation.RequestMapping;
import io.basc.start.app.address.model.AddressTree;
import io.basc.start.app.address.pojo.Address;
import io.basc.start.app.address.service.AddressService;

import java.util.Collections;
import java.util.List;

@RequestMapping(value = "address", methods = { HttpMethod.GET, HttpMethod.POST })
public class AddressController {
	private AddressService addressService;
	@Autowired
	private ResultFactory resultFactory;

	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@RequestMapping(value = "sub_list")
	public Result subList(int id) {
		List<Address> list = addressService.getAddressSubList(id);
		if (list == null) {
			list = Collections.emptyList();
		}
		return resultFactory.success(list);
	}

	@RequestMapping(value = "list")
	public Result list(ServerHttpRequest request, ServerHttpResponse response) {
		if (!WebUtils.isExpired(request, response, addressService.lastModified())) {
			return null;
		}

		List<Address> list = addressService.getAddressList();
		if (list == null) {
			list = Collections.emptyList();
		}
		return resultFactory.success(list);
	}

	@RequestMapping(value = "trees")
	public Result tree(ServerHttpRequest request, ServerHttpResponse response) {
		if (!WebUtils.isExpired(request, response, addressService.lastModified())) {
			return null;
		}

		List<AddressTree> trees = addressService.getAddressTrees();
		if (trees == null) {
			trees = Collections.emptyList();
		}
		return resultFactory.success(trees);
	}

	@RequestMapping(value = "roots")
	public Result roots() {
		List<Address> list = addressService.getRootAddressList();
		if (list == null) {
			list = Collections.emptyList();
		}
		return resultFactory.success(list);
	}
}
