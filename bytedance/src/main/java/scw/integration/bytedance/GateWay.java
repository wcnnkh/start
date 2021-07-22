package scw.integration.bytedance;

public enum GateWay {
	DOUYIN("https://open.douyin.com/"), SNSSDK("https://open.snssdk.com/"), IXIGUA("https://open-api.ixigua.com/");

	private final String url;

	GateWay(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}
}
