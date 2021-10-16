package io.basc.start.app.user.enums;

import io.basc.framework.mapper.Field;
import io.basc.framework.mapper.FieldFeature;
import io.basc.framework.mapper.MapperUtils;
import io.basc.start.app.user.pojo.User;

public enum AccountType {
	USERNAME("username"), 
	PHONE("phone"),
	EMAIL("email"), 
	;
	
	private final String fieldName;
	
	AccountType(String fieldName){
		this.fieldName = fieldName;
	}
	
	public String getFieldName() {
		return fieldName;
	}

	public Field getField(){
		return MapperUtils.getFields(User.class).all().accept(FieldFeature.EXISTING_GETTER_FIELD).find(fieldName, String.class);
	}

	public String getAccount(User user) {
		return (String) getField().getGetter().get(user);
	}

	public void setAccount(User user, String account) {
		getField().getSetter().set(user, account);
	}
}
