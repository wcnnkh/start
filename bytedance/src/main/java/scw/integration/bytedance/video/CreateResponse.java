package scw.integration.bytedance.video;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.ResponseCode;

public class CreateResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "抖音视频id", example = "@8hxdhauTCMppanGnM4ltGM780mDqPP+KPpR0qQOmLVAXb/T060zdRmYqig357zEBq6CZRp4NVe6qLIJW/V/x1w==\r\n")
	private String item_id;

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
}
