package io.basc.start.template.pojo;

import io.basc.framework.orm.annotation.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity(primaryKeys = { "templateId", "name" })
public class AttributeDescribe extends AttributeTemplateDescribe {
	private static final long serialVersionUID = 1L;
	private String value;
	private int type;
}