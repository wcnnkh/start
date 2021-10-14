package io.basc.start.app.address.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import io.basc.framework.beans.annotation.Service;
import io.basc.framework.beans.annotation.Value;
import io.basc.framework.convert.annotation.JSON;
import io.basc.framework.env.Environment;
import io.basc.framework.io.Resource;
import io.basc.framework.json.JsonArray;
import io.basc.framework.json.JsonElement;
import io.basc.framework.json.JsonObject;
import io.basc.framework.logger.Logger;
import io.basc.framework.logger.LoggerFactory;
import io.basc.framework.mapper.Copy;
import io.basc.framework.util.CollectionUtils;
import io.basc.start.app.address.model.AddressTree;
import io.basc.start.app.address.pojo.Address;
import io.basc.start.app.address.service.AddressService;

@Service
public class LocalAddressServiceImpl implements AddressService {
	private static final String RESOURCE = "classpath:/io/basc/integration/app/address/service/address.json";

	private static Logger logger = LoggerFactory.getLogger(LocalAddressServiceImpl.class);
	private volatile Map<Integer, Address> addressMap = Collections.emptyMap();
	private volatile List<Address> rootAddressList = Collections.emptyList();
	private volatile Map<Integer, List<Address>> subListMap = Collections.emptyMap();
	private Environment environment;

	public LocalAddressServiceImpl(Environment environment) {
		this.environment = environment;
	}

	@JSON
	@Value(value = RESOURCE)
	public void setLocalAddressJson(JsonObject jsonObject) {
		List<Address> list = parseAddressList(jsonObject);
		if (CollectionUtils.isEmpty(list)) {
			return;
		}

		Map<Integer, Address> addressMap = new LinkedHashMap<Integer, Address>();
		List<Address> rootAddressList = new ArrayList<Address>();
		Map<Integer, List<Address>> subListMap = new HashMap<Integer, List<Address>>();

		for (Address address : list) {
			addressMap.put(address.getId(), address);
		}

		for (Address address : list) {
			if (!addressMap.containsKey(address.getParentId())) {
				rootAddressList.add(address);
			}
		}

		for (Address address : list) {
			List<Address> subList = new ArrayList<Address>();
			for (Address ads : list) {
				if (ads.getParentId() == address.getId()) {
					subList.add(ads);
				}
			}
			if (!subList.isEmpty()) {
				subListMap.put(address.getId(), subList);
			}
		}

		this.addressMap = addressMap;
		this.rootAddressList = rootAddressList;
		this.subListMap = subListMap;
	}

	private List<Address> parseAddressList(JsonObject jsonObject) {
		JsonArray provinces = getSubList(jsonObject, "provinces", "province");
		if (provinces == null || provinces.size() == 0) {
			return null;
		}

		List<Address> list = new ArrayList<Address>();
		for (int i = 0; i < provinces.size(); i++) {
			JsonObject item = provinces.getJsonObject(i);
			Address province = parseAddress(item, 0);
			list.add(province);

			JsonArray citys = getSubList(item, "cities", "city");
			if (citys == null || provinces.isEmpty()) {
				continue;
			}

			for (JsonElement jsonElement : citys) {
				JsonObject cityJsonObject = jsonElement.getAsJsonObject();
				Address city = parseAddress(cityJsonObject, province.getId());
				list.add(city);
				JsonArray areas = getSubList(cityJsonObject, "areas", "area");
				if (areas == null || areas.isEmpty()) {
					continue;
				}

				for (JsonElement areaJsonElemnt : areas) {
					JsonObject areaJsonObject = areaJsonElemnt.getAsJsonObject();
					Address area = parseAddress(areaJsonObject, city.getId());
					list.add(area);
				}
			}
		}
		return list;
	}

	private JsonArray getSubList(JsonObject jsonObject, String objectKey, String subListKey) {
		JsonObject provinces = jsonObject.getJsonObject(objectKey);
		if (provinces == null || provinces.isEmpty()) {
			return null;
		}

		return provinces.getJsonArray(subListKey);
	}

	private Address parseAddress(JsonObject jsonObject, int parentId) {
		int id = jsonObject.getIntValue("ssqid");
		String name = jsonObject.getString("ssqname");
		Address address = new Address();
		address.setId(id);
		address.setName(name);
		address.setParentId(parentId);
		return address;
	}

	public Address getById(int id) {
		Address address = addressMap.get(id);
		return address == null ? null : address.clone();
	}

	public List<Address> getParents(int id, boolean includeSelf) {
		List<Address> addresses = new ArrayList<Address>();
		Address address = getById(id);
		if (includeSelf) {
			addresses.add(address);
		}

		while (true) {
			Address parent = getById(address.getParentId());
			if (parent == null) {
				break;
			}

			addresses.add(parent);
			// 理论上地址的层级不可能超过10层，如果出现这种情况了可能是数据有问题导致死循环
			if (addresses.size() > 10) {
				logger.error("理论上地址的层级不可能超过10层，如果出现这种情况了可能是数据有问题导致死循环! id={}", id);
				break;
			}
		}

		if (addresses.isEmpty()) {
			return Collections.emptyList();
		}

		return CollectionUtils.reversal(addresses);
	}

	public List<Address> getAddressSubList(int id) {
		List<Address> list = subListMap.get(id);
		if (list == null) {
			return Collections.emptyList();
		}

		return Collections.unmodifiableList(list);
	}

	public List<Address> getRootAddressList() {
		return Collections.unmodifiableList(rootAddressList);
	}

	public List<Address> getAddressList() {
		Collection<Address> addresses = addressMap.values();
		if (CollectionUtils.isEmpty(addresses)) {
			return Collections.emptyList();
		}

		return new ArrayList<Address>(addresses);
	}

	private List<AddressTree> getAddressTree(List<Address> addresses) {
		if (CollectionUtils.isEmpty(addresses)) {
			return Collections.emptyList();
		}

		List<AddressTree> addressTrees = new ArrayList<AddressTree>(addresses.size());
		for (Address address : addresses) {
			AddressTree tree = new AddressTree();
			Copy.copy(address, tree);
			tree.setSubList(getAddressTree(getAddressSubList(tree.getId())));
			addressTrees.add(tree);
		}
		return addressTrees;
	}

	public List<AddressTree> getAddressTrees() {
		return getAddressTree(getRootAddressList());
	}

	public long lastModified() {
		Resource resource = environment.getResource(RESOURCE);
		if (resource == null || !resource.exists()) {
			return -1;
		}

		try {
			return resource.lastModified();
		} catch (IOException e) {
			logger.error(e, resource.getDescription());
			return -1;
		}
	}
}
