package io.basc.start.bytedance.data;

public enum AmusementRankApiType {
	/**
	 * 搞笑总榜
	 */
	OVERALL("/data/extern/billboard/amusement/overall/"),
	/**
	 * 搞笑新势力
	 */
	NEW("/data/extern/billboard/amusement/new/");

	private final String api;

	private AmusementRankApiType(String api) {
		this.api = api;
	}

	public String getApi() {
		return api;
	}
}
