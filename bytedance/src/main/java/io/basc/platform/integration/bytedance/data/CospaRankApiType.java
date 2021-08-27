package io.basc.platform.integration.bytedance.data;

public enum CospaRankApiType {
	/**
	 * 二次元总榜
	 */
	OVERALL("/data/extern/billboard/cospa/overall/"),
	/**
	 * 轻漫
	 */
	QING_MAN("/data/extern/billboard/cospa/qing_man/"),
	/**
	 * 出境拍摄
	 */
	OUT_SHOT("/data/extern/billboard/cospa/out_shot/"),
	/**
	 * 绘画
	 */
	PAINTING("/data/extern/billboard/cospa/painting/"),
	/**
	 * 声控
	 */
	VOICE_CONTROL("/data/extern/billboard/cospa/voice_control/"),
	/**
	 * 脑洞
	 */
	BRAIN_CAVITY("/data/extern/billboard/cospa/brain_cavity/"),
	/**
	 * 二次元新势力
	 */
	NEW("/data/extern/billboard/cospa/new/"),;

	private final String api;

	private CospaRankApiType(String api) {
		this.api = api;
	}

	public String getApi() {
		return api;
	}
}
