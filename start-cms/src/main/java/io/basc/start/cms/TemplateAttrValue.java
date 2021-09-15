package io.basc.start.cms;

import java.io.Serializable;

import io.basc.framework.orm.annotation.Entity;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
public class TemplateAttrValue implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "属性横版")
	private long templateId;
	@Schema(description = "属性id")
	private long attrId;
	@Schema(description = "属性值")
	private String value;

	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	public long getAttrId() {
		return attrId;
	}

	public void setAttrId(long attrId) {
		this.attrId = attrId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
