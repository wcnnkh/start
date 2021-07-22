package scw.integration.bytedance;

public enum GateWay {
	/**
	 * 抖音
	 */
	DOUYIN("https://open.douyin.com/", ""),
	/**
	 * 头条
	 */
	SNSSDK("https://open.snssdk.com/", "/toutiao"),
	/**
	 * 西瓜
	 */
	IXIGUA("https://open-api.ixigua.com/", "/xigua");

	private final String url;
	private final String videoContextPath;

	GateWay(String url, String videoContextPath) {
		this.url = url;
		this.videoContextPath = videoContextPath;
	}

	public String getUrl() {
		return url;
	}

	public String getVideoContextPath() {
		return videoContextPath;
	}
}
