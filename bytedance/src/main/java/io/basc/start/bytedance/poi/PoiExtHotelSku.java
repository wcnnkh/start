package io.basc.start.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class PoiExtHotelSku implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "价格时间区间[start_date, end_date)")
	private String start_date;
	@Schema(description = "库存量", example = "库存量")
	private Long stock;
	@Schema(description = "价格时间区间[start_date, end_date)")
	private String end_date;
	@Schema(description = "	房型价格(人民币分)")
	private Long price;
	@Schema(description = "接入方SPU ID")
	private String spu_ext_id;

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getSpu_ext_id() {
		return spu_ext_id;
	}

	public void setSpu_ext_id(String spu_ext_id) {
		this.spu_ext_id = spu_ext_id;
	}
}
