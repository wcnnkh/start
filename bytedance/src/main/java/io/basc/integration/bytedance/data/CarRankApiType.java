package io.basc.integration.bytedance.data;

public enum CarRankApiType {
	/**
	 * 汽车总榜
	 */
	OVERALL("/data/extern/billboard/car/overall/"),
	/**
	 * 评车榜
	 */
	COMMENT("/data/extern/billboard/car/comment/"),
	/**
	 * 玩车榜
	 */
	PLAY("/data/extern/billboard/car/play/"),
	/**
	 * 用车榜
	 */
	USE("/data/extern/billboard/car/use/"),
	/**
	 * 驾考榜
	 */
	DIRVER("/data/extern/billboard/car/driver/");

	private final String api;

	private CarRankApiType(String api) {
		this.api = api;
	}

	public String getApi() {
		return api;
	}
}
