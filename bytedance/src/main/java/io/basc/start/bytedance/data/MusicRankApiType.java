package io.basc.start.bytedance.data;

public enum MusicRankApiType {
	/**
	 * 热歌榜
	 */
	HOT("/data/extern/billboard/music/hot/"),
	/**
	 * 飙升榜
	 */
	SOAR("/data/extern/billboard/music/soar/"),
	/**
	 * 原创榜
	 */
	ORIGINAL("/data/extern/billboard/music/original/");

	private final String api;

	private MusicRankApiType(String api) {
		this.api = api;
	}

	public String getApi() {
		return api;
	}
}
