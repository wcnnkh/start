package io.basc.start.bytedance.poi;

import javax.validation.constraints.NotNull;

import io.basc.start.bytedance.oauth.ClientRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class PoiV2SpuGetRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "第三方SPU ID", example = "x0001", required = true)
	@NotNull
	private String spu_ext_id;
	@Schema(description = "是否需要商品草稿数据(带有商品的审核状态，只展示最近30天的数据，并且最多最近提交的20次内)", example = "true", required = true)
	@NotNull
	private Boolean need_spu_draft;
	@Schema(description = "需要商品草稿数据的数目(0-20)，超过这个范围默认返回20个")
	private Long spu_draft_count;

	public String getSpu_ext_id() {
		return spu_ext_id;
	}

	public void setSpu_ext_id(String spu_ext_id) {
		this.spu_ext_id = spu_ext_id;
	}

	public Boolean getNeed_spu_draft() {
		return need_spu_draft;
	}

	public void setNeed_spu_draft(Boolean need_spu_draft) {
		this.need_spu_draft = need_spu_draft;
	}

	public Long getSpu_draft_count() {
		return spu_draft_count;
	}

	public void setSpu_draft_count(Long spu_draft_count) {
		this.spu_draft_count = spu_draft_count;
	}

}
