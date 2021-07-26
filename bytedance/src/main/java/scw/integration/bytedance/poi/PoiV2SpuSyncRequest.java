package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PoiV2SpuSyncRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "spu类型号，1-日历房，30-酒店民宿预售券，90-门票，91-团购券", required = true)
	@NotNull
	private Long spu_type;
	@Schema(description = "商品亮点标签", example = "[map[content:\"免费\": priority:1:]]")
	private String[] highlights;
	@Schema(description = "商品名", example = "预售券1", required = true)
	@NotNull
	private String name;
	@Schema(description = "接入方店铺ID列表", example = "[y0001 y0002]", required = true)
	@NotNull
	private String supplier_ext_id_list;
	@Schema(description = "SPU图片，预售券必传", example = "[url1 url2]", required = true)
	@NotEmpty
	private List<String> image_list;
	@Schema(description = "推荐语，5~20个字")
	private String recommend_word;
	@Schema(description = "接入方商品ID", required = true)
	@NotNull
	private String spu_ext_id;
	@Schema(description = "SPU状态， 1 - 在线; 2 - 下线", required = true)
	@NotNull
	private Long status;
	@Schema(description = "库存", required = true)
	@NotNull
	private Long stock;
	@Schema(description = "价格，单位分", required = true)
	@NotNull
	private Long price;
	@Schema(description = "排序权重，按降序排列")
	private Long sort_weight;
	@Schema(description = "前台品类标签", example = "[一日游]")
	private String[] front_category_tag;
	@Schema(description = "价格，单位分", required = true)
	@NotNull
	private Long origin_price;
	@Schema(description = "商品的抽佣率，万分数", example = "100，表示抽佣万分之一百，即1%	")
	private Long take_rate;
	@Schema(description = "SPU属性字段")
	private Map<String, String> attribute;
	@Schema(description = "入口信息")
	private EntryInfo<EntryMiniApp> entry_info;

	public Long getSpu_type() {
		return spu_type;
	}

	public void setSpu_type(Long spu_type) {
		this.spu_type = spu_type;
	}

	public String[] getHighlights() {
		return highlights;
	}

	public void setHighlights(String[] highlights) {
		this.highlights = highlights;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupplier_ext_id_list() {
		return supplier_ext_id_list;
	}

	public void setSupplier_ext_id_list(String supplier_ext_id_list) {
		this.supplier_ext_id_list = supplier_ext_id_list;
	}

	public List<String> getImage_list() {
		return image_list;
	}

	public void setImage_list(List<String> image_list) {
		this.image_list = image_list;
	}

	public String getRecommend_word() {
		return recommend_word;
	}

	public void setRecommend_word(String recommend_word) {
		this.recommend_word = recommend_word;
	}

	public String getSpu_ext_id() {
		return spu_ext_id;
	}

	public void setSpu_ext_id(String spu_ext_id) {
		this.spu_ext_id = spu_ext_id;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
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

	public String[] getFront_category_tag() {
		return front_category_tag;
	}

	public void setFront_category_tag(String[] front_category_tag) {
		this.front_category_tag = front_category_tag;
	}

	public Long getOrigin_price() {
		return origin_price;
	}

	public void setOrigin_price(Long origin_price) {
		this.origin_price = origin_price;
	}

	public Long getTake_rate() {
		return take_rate;
	}

	public void setTake_rate(Long take_rate) {
		this.take_rate = take_rate;
	}

	public Map<String, String> getAttribute() {
		return attribute;
	}

	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}

	public EntryInfo<EntryMiniApp> getEntry_info() {
		return entry_info;
	}

	public void setEntry_info(EntryInfo<EntryMiniApp> entry_info) {
		this.entry_info = entry_info;
	}
}
