package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class PoiV2SpuStockUpdateRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	@Schema(description = "接入方商品ID")
	private String spu_ext_id;
	@Schema(description = "库存")
	private Long stock;
	public String getSpu_ext_id() {
		return spu_ext_id;
	}
	public void setSpu_ext_id(String spu_ext_id) {
		this.spu_ext_id = spu_ext_id;
	}
	public Long getStock() {
		return stock;
	}
	public void setStock(Long stock) {
		this.stock = stock;
	}
}
