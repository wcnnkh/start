package io.basc.integration.bytedance.data;

public enum TravelRankApiType {
	/**
	 * 旅游总榜
	 */
	OVERALL("/data/extern/billboard/travel/overall/"),
	/**
	 * 旅游新势力榜
	 */
	NEW("/data/extern/billboard/travel/new/");

	private final String api;

	private TravelRankApiType(String api) {
		this.api = api;
	}

	public String getApi() {
		return api;
	}
}
