package io.basc.start.cms.pojo;

import java.util.Map;

import io.basc.start.template.pojo.Attribute;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryInfo extends Category {
	private static final long serialVersionUID = 1L;
	private Map<String, Attribute> attributes;
}
