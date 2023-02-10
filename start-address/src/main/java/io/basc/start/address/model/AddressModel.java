package io.basc.start.address.model;

import java.io.Serializable;

import io.basc.framework.orm.annotation.Comment;
import lombok.Data;

@Data
public class AddressModel implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	private String name;
	@Comment("经度")
	private float longitude;
	@Comment("纬度")
	private float latitude;
}
