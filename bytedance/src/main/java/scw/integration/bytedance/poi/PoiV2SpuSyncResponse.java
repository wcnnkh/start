package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.ResponseCode;

public class PoiV2SpuSyncResponse extends ResponseCode{
	private static final long serialVersionUID = 1L;
	@Schema(description = "抖音平台SPU ID")
	private String spu_id;

	public String getSpu_id() {
		return spu_id;
	}

	public void setSpu_id(String spu_id) {
		this.spu_id = spu_id;
	}
}
