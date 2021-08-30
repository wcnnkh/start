package io.basc.start.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

public class PoiV2SpuStatusSyncRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "接入方商品ID列表", required = true)
	private List<String> spu_ext_id_list;
	@Schema(description = "SPU状态， 1 - 在线; 2 - 下线", required = true)
	private Long status;

	public List<String> getSpu_ext_id_list() {
		return spu_ext_id_list;
	}

	public void setSpu_ext_id_list(List<String> spu_ext_id_list) {
		this.spu_ext_id_list = spu_ext_id_list;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
}
