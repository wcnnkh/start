package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.ResponseCode;

public class PoiV2SpuStatusResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "抖音商品ID")
	private String spu_id;
	@Schema(description = "状态：0-审核中，1-上架，2-下架，3-审核拒绝")
	private Long status;
	@Schema(description = "状态描述")
	private String status_desc;

	public String getSpu_id() {
		return spu_id;
	}

	public void setSpu_id(String spu_id) {
		this.spu_id = spu_id;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getStatus_desc() {
		return status_desc;
	}

	public void setStatus_desc(String status_desc) {
		this.status_desc = status_desc;
	}
}
