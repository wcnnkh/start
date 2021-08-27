package io.basc.platform.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.Map;

public class Sku implements Serializable{
	private static final long serialVersionUID = 1L;
	@Schema(description = "SKU属性字段")
	private Map<String, String> attributes;
	@Schema(description = "价格(人民币分)", example = "69800")
	private Long price;
	@Schema(description = "在线状态 1 - 在线; 2 - 下线", example = "1")
	private Long status;
	@Schema(description = "库存数量", example = "2")
	private Long stock;
}
