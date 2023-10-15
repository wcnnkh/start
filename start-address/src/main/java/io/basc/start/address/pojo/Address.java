package io.basc.start.address.pojo;

import io.basc.framework.jdbc.template.annotation.Table;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.start.address.model.AddressModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Table
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Address extends AddressModel implements Cloneable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private int id;
	@PrimaryKey
	private int parentId;

	@Override
	public Address clone() {
		try {
			return (Address) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
}
