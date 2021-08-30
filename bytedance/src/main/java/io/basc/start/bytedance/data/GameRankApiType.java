package io.basc.start.bytedance.data;

public enum GameRankApiType {
	/**
	 * 单机主机榜
	 */
	CONSOLE("/data/extern/billboard/game/console/"),
	/**
	 * 游戏资讯
	 */
	INF("/data/extern/billboard/game/inf/");

	private final String api;

	private GameRankApiType(String api) {
		this.api = api;
	}

	public String getApi() {
		return api;
	}
}
