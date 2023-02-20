package io.basc.start.cms.pojo;

import java.io.Serializable;

import io.basc.framework.orm.annotation.PrimaryKey;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "sku库存日志")
public class SkuStockLog implements Serializable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private String id;
	private long skuId;
	@Schema(description = "库存变化")
	private int stock;
	@Schema(description = "描述")
	private String description;
	@Schema(description = "来源类型")
	private int sourceType;
	@Schema(description = "操作人")
	private String operator;
	@Schema(description = "创建时间")
	private long cts;
}
