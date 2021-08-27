package io.basc.platform.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SpuDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "入口信息")
	private EntryInfo<EntryMiniApp> entry_info;
	@Schema(description = "接入方店铺ID列表")
	private List<String> supplier_ext_id_list;
	@Schema(description = "商品的抽佣率，万分数", example = "100")
	private Long take_rate;
	@Schema(description = "前台品类标签")
	private String[] front_category_tag;
	@Schema(description = "SPU图片，预售券必传", example = "[url1 url2]")
	private List<String> image_list;
	@Schema(description = "商品名", example = "预售券1")
	private String name;
	@Schema(description = "库存")
	private Long stock;
	@Schema(description = "商品亮点标签")
	private Highlight[] highlights;
	@Schema(description = "	SPU属性字段")
	private Map<String, String> attribute;
	@Schema(description = "价格，单位分")
	private Long origin_price;
	@Schema(description = "价格，单位分")
	private Long price;
	@Schema(description = "排序权重，按降序排列")
	private Long sort_weight;
	@Schema(description = "接入方商品ID")
	private String spu_ext_id;
	@Schema(description = "spu类型号，1-日历房，30-酒店民宿预售券，90-门票，91-团购券")
	private Long spu_type;
	@Schema(description = "状态描述")
	private String status_desc;

	public EntryInfo<EntryMiniApp> getEntry_info() {
		return entry_info;
	}

	public void setEntry_info(EntryInfo<EntryMiniApp> entry_info) {
		this.entry_info = entry_info;
	}

	public List<String> getSupplier_ext_id_list() {
		return supplier_ext_id_list;
	}

	public void setSupplier_ext_id_list(List<String> supplier_ext_id_list) {
		this.supplier_ext_id_list = supplier_ext_id_list;
	}

	public Long getTake_rate() {
		return take_rate;
	}

	public void setTake_rate(Long take_rate) {
		this.take_rate = take_rate;
	}

	public String[] getFront_category_tag() {
		return front_category_tag;
	}

	public void setFront_category_tag(String[] front_category_tag) {
		this.front_category_tag = front_category_tag;
	}

	public List<String> getImage_list() {
		return image_list;
	}

	public void setImage_list(List<String> image_list) {
		this.image_list = image_list;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public Highlight[] getHighlights() {
		return highlights;
	}

	public void setHighlights(Highlight[] highlights) {
		this.highlights = highlights;
	}

	public Map<String, String> getAttribute() {
		return attribute;
	}

	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}

	public Long getOrigin_price() {
		return origin_price;
	}

	public void setOrigin_price(Long origin_price) {
		this.origin_price = origin_price;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getSort_weight() {
		return sort_weight;
	}

	public void setSort_weight(Long sort_weight) {
		this.sort_weight = sort_weight;
	}

	public String getSpu_ext_id() {
		return spu_ext_id;
	}

	public void setSpu_ext_id(String spu_ext_id) {
		this.spu_ext_id = spu_ext_id;
	}

	public Long getSpu_type() {
		return spu_type;
	}

	public void setSpu_type(Long spu_type) {
		this.spu_type = spu_type;
	}

	public String getStatus_desc() {
		return status_desc;
	}

	public void setStatus_desc(String status_desc) {
		this.status_desc = status_desc;
	}

	public static class EntryMiniApp implements Serializable {
		private static final long serialVersionUID = 1L;
		@Schema(description = "入口类型(1:H5，2:抖音小程序，3:抖音链接)")
		private String entry_type;
		@Schema(description = "入口链接")
		private String entry_url;

		public String getEntry_type() {
			return entry_type;
		}

		public void setEntry_type(String entry_type) {
			this.entry_type = entry_type;
		}

		public String getEntry_url() {
			return entry_url;
		}

		public void setEntry_url(String entry_url) {
			this.entry_url = entry_url;
		}
	}
}
