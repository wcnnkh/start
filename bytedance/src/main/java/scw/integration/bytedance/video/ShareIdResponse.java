package scw.integration.bytedance.video;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.ResponseCode;

public class ShareIdResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "share_id", example = "15674132978")
	private String share_id;

	public String getShare_id() {
		return share_id;
	}

	public void setShare_id(String share_id) {
		this.share_id = share_id;
	}
}
