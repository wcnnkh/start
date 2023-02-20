package io.basc.start.template.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Attribute extends AttributeValue {
	private static final long serialVersionUID = 1L;
	private AttributeDescribe describe;
}
