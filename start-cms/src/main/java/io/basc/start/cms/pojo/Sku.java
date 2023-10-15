package io.basc.start.cms.pojo;

import io.basc.framework.jdbc.template.annotation.Index;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.start.template.pojo.AttributeTemplateDescribe;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 规格
 * 
 * @author wcnnkh
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Sku extends AttributeTemplateDescribe {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Schema(description = "规格id")
	private long id;
	@Index
	private long parentId;
	@Index
	private long contentId;
	@Schema(description = "库存")
	private long stock;
	@Schema(description = "价格(分)")
	private long price;
}
