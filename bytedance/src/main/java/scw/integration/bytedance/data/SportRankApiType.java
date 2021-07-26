package scw.integration.bytedance.data;

public enum SportRankApiType {
	/**
	 * 体育总榜
	 */
	OVERALL("/data/extern/billboard/sport/overall/"),
	/**
	 * 篮球榜
	 */
	BASKETBALL("/data/extern/billboard/sport/basketball/"),
	/**
	 * 足球榜
	 */
	SOCCER("/data/extern/billboard/sport/soccer/"),
	/**
	 * 综合体育榜
	 */
	COMPAREHENSIVE("/data/extern/billboard/sport/comprehensive/"),
	/**
	 * 运动健身榜
	 */
	FITNESS("/data/extern/billboard/sport/fitness/"),

	/**
	 * 户外运动榜
	 */
	OUTDOORS("/data/extern/billboard/sport/outdoors/"),

	/**
	 * 台球榜
	 */
	TABLE_TENNIS("/data/extern/billboard/sport/table_tennis/"),
	/**
	 * 运动文化榜
	 */
	CULTURE("/data/extern/billboard/sport/culture/"),;

	private final String api;

	private SportRankApiType(String api) {
		this.api = api;
	}

	public String getApi() {
		return api;
	}
}
