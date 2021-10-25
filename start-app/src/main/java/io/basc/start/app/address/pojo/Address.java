package io.basc.start.app.address.pojo;

import io.basc.framework.mapper.MapperUtils;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.framework.sql.orm.annotation.Table;
import io.basc.start.app.address.model.AddressModel;

@Table
public class Address extends AddressModel implements Cloneable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private int id;
	@PrimaryKey
	private int parentId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	@Override
	public Address clone() {
		try {
			return (Address) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String toString() {
		return MapperUtils.toString(this);
	}
}
