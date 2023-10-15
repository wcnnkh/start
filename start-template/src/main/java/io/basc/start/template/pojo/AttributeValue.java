package io.basc.start.template.pojo;

import java.io.Serializable;

import io.basc.framework.jdbc.template.annotation.Table;
import io.basc.framework.orm.annotation.PrimaryKey;
import lombok.Data;

@Data
@Table
public class AttributeValue implements Serializable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private long sourceId;
	@PrimaryKey
	private String name;
	private String value;
}
