package io.basc.integration.bytedance.poi;

import java.io.Serializable;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

public class Spu implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "接入方SPU ID")
	private String spu_ext_id;
	@Schema(description = "spu类型号，1-酒店民宿房型，90-景区门票，91-团购券 20 电商实体商品 21 电商虚拟商品")
	private Long spu_type;
	@Schema(description = "在线状态 1 - 在线; 2 - 下线")
	private Long status;
	@Schema(description = "接入方店铺ID")
	private String supplier_ext_id;
	@Schema(description = "SPU属性字段")
	private Map<Integer, String> attributes;
	@Schema(description = "SPU描述")
	private String description;
	@Schema(description = "SPU名称")
	private String name;
	@Schema(description = "SPU展示顺序,降序", example = "")
	private Long order;

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

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public String getSupplier_ext_id() {
		return supplier_ext_id;
	}

	public void setSupplier_ext_id(String supplier_ext_id) {
		this.supplier_ext_id = supplier_ext_id;
	}

	public Map<Integer, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<Integer, String> attributes) {
		this.attributes = attributes;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getOrder() {
		return order;
	}

	public void setOrder(Long order) {
		this.order = order;
	}
}
