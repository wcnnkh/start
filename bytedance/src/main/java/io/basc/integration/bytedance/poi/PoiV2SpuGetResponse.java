package io.basc.integration.bytedance.poi;

import io.basc.integration.bytedance.ResponseCode;

public class PoiV2SpuGetResponse extends ResponseCode{
	private static final long serialVersionUID = 1L;
	private SpuDetail spu_detail;
	private SpuDetail[] spu_draft;
	public SpuDetail getSpu_detail() {
		return spu_detail;
	}
	public void setSpu_detail(SpuDetail spu_detail) {
		this.spu_detail = spu_detail;
	}
	public SpuDetail[] getSpu_draft() {
		return spu_draft;
	}
	public void setSpu_draft(SpuDetail[] spu_draft) {
		this.spu_draft = spu_draft;
	}
}
