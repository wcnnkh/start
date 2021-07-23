package scw.integration.bytedance.poi;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

public class PoiSupplier implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "接入方店铺id", example = "x0001", required = true)
	@NotNull
	private String supplier_ext_id;
	@Schema(description = "POI品类编码")
	private String type_code;
	@Schema(description = "店铺地址", example = "栏学路328弄97号")
	private String address;
	@Schema(description = "联系手机号", example = "189xxxx8929")
	private String contact_phone;
	@Schema(description = "经度")
	private String longitude;
	@Schema(description = "推荐")
	private List<Recommend> recommends;
	@Schema(description = "店铺提供的服务列表")
	private List<Service> services;
	@Schema(description = "在线状态 1 - 在线; 2 - 下线", example = "1", required = true)
	@NotNull
	private Long status;
	@Schema(description = "店铺类型 1 - 酒店民宿 2 - 餐饮 3 - 景区 4 - 电商 5 - 教育 6 - 丽人 7 - 爱车 8 - 亲子 9 - 宠物 10 - 家装 11 - 娱乐场所 12 - 图文快印	", required = true)
	@NotNull
	private Long type;
	// TODO attributes 文档太垃圾了
	@Schema(description = "店铺属性字段，编号规则：垂直行业 1xxx-酒店民宿 2xxx-餐饮 3xxx-景区 通用属性-9yxxx", required = true)
	@NotEmpty
	private Map<Integer, String> attributes;
	@Schema(description = "人均消费（单位分）")
	private Long avg_cost;
	@Schema(description = "纬度")
	private String latitude;
	@Schema(description = "联系座机号", example = "021-58xxxx95")
	private String contact_tel;
	@Schema(description = "店铺名称", example = "上海几茶.知更民宿（迪士尼店）")
	private String name;
	@Schema(description = "抖音poi id, 三方如果使用高德poi id可以通过/poi/query/接口转换，其它三方poi id走poi匹配功能进行抖音poi id获取", example = "6601136930455291912", required = true)
	@NotNull
	private String poi_id;
	@Schema(description = "标签", example = "[可停车 离地铁近]")
	private String[] tags;
	@Schema(description = "POI品类描述 eg. 美食;中式餐饮;小龙虾")
	private String type_name;
	@Schema(description = "店铺介绍(<=500字)")
	@Size(max = 500)
	private String description;
	@Schema(description = "店铺图片", example = "[https://static.dingdandao.com/0ffaf1b5a669ea216a960c97ded9a3b0 https://static.dingdandao.com/9119b617eff7c8fe2be318da0459d4db]")
	private String[] images;
	@Schema(description = "营业时间, 从周一到周日，list长度为7，不营业则为空字符串", example = "[10:00-12:00;13:00-22:00 10:00-12:00;13:00-22:00 10:00-12:00;13:00-22:00 10:00-12:00;13:00-22:00 10:00-12:00;13:00-22:00 10:00-12:00;13:00-22:00]")
	private String[] open_time;

	public String getSupplier_ext_id() {
		return supplier_ext_id;
	}

	public void setSupplier_ext_id(String supplier_ext_id) {
		this.supplier_ext_id = supplier_ext_id;
	}

	public String getType_code() {
		return type_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact_phone() {
		return contact_phone;
	}

	public void setContact_phone(String contact_phone) {
		this.contact_phone = contact_phone;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public List<Recommend> getRecommends() {
		return recommends;
	}

	public void setRecommends(List<Recommend> recommends) {
		this.recommends = recommends;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Map<Integer, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<Integer, String> attributes) {
		this.attributes = attributes;
	}

	public Long getAvg_cost() {
		return avg_cost;
	}

	public void setAvg_cost(Long avg_cost) {
		this.avg_cost = avg_cost;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getContact_tel() {
		return contact_tel;
	}

	public void setContact_tel(String contact_tel) {
		this.contact_tel = contact_tel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(String poi_id) {
		this.poi_id = poi_id;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	public String[] getOpen_time() {
		return open_time;
	}

	public void setOpen_time(String[] open_time) {
		this.open_time = open_time;
	}
}
