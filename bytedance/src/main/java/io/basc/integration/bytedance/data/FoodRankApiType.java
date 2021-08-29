package io.basc.integration.bytedance.data;

public enum FoodRankApiType {
	/**
	 * 美食总榜
	 */
	OVERALL("/data/extern/billboard/food/overall/"),
	/**
	 * 美食新势力榜
	 */
	NEW("/data/extern/billboard/food/new/"),
	/**
	 * 美食教程榜
	 */
	TUTORIAL("/data/extern/billboard/food/tutorial/"),
	/**
	 * 美食探店榜
	 */
	SHOP("/data/extern/billboard/food/shop/");

	private final String api;

	private FoodRankApiType(String api) {
		this.api = api;
	}

	public String getApi() {
		return api;
	}
}
