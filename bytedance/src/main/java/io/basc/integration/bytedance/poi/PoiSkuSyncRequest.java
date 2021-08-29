package io.basc.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PoiSkuSyncRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(required = true)
	@NotEmpty
	private List<Sku> skus;
	@Schema(description = "外部平台SPU ID", example = "y0001", required = true)
	@NotNull
	private String spu_ext_id;

	public List<Sku> getSkus() {
		return skus;
	}

	public void setSkus(List<Sku> skus) {
		this.skus = skus;
	}

	public String getSpu_ext_id() {
		return spu_ext_id;
	}

	public void setSpu_ext_id(String spu_ext_id) {
		this.spu_ext_id = spu_ext_id;
	}
}
