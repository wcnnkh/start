package io.basc.start.usercenter.enums;

import io.basc.framework.mapper.Element;
import io.basc.framework.mapper.Element;
import io.basc.framework.mapper.Element;
import io.basc.start.usercenter.pojo.User;

public enum AccountType {
	USERNAME("username"), PHONE("phone"), EMAIL("email"),;

	private final String fieldName;

	AccountType(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Element getField() {
		return Fields.getFields(User.class).all().filter(FieldFeature.EXISTING_GETTER_FIELD).getByName(fieldName,
				String.class);
	}

	public String getAccount(User user) {
		return (String) getField().get(user);
	}

	public void setAccount(User user, String account) {
		getField().getSetter().set(user, account);
	}
}
