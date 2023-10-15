package io.basc.start.cms.pojo;

import io.basc.framework.jdbc.template.annotation.Index;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.start.template.pojo.AttributeTemplateDescribe;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Content extends AttributeTemplateDescribe {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Schema(description = "内容id")
	private long id;
	@Index
	private long categoryId;
}
