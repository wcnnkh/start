package scw.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalAnchorMpItemClickDistribution extends DataExternalMetadata {
	private static final long serialVersionUID = 1L;
	@Schema(description = "小程序点击量视频分布", example = "{\"视频1\":9,\"视频2\":16}")
	private String mp_item_click_json;

	public String getMp_item_click_json() {
		return mp_item_click_json;
	}

	public void setMp_item_click_json(String mp_item_click_json) {
		this.mp_item_click_json = mp_item_click_json;
	}
}
