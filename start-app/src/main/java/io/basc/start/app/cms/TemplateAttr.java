package io.basc.start.app.cms;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.basc.framework.orm.annotation.Entity;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "模板属性")
@Entity
public class TemplateAttr implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "属性id", required = true)
	@PrimaryKey
	private long id;
	@Schema(description = "模板id", required = true)
	@NotNull
	private long templateId;
	@Schema(description = "属性名称(对一个模板而言应该是唯一的)", required = true)
	@NotEmpty
	private String name;
	@Schema(description = "属性类型", required = true)
	@NotNull
	private AttrType type;
	@Schema(description = "属性描述", required = true)
	@NotEmpty
	private String describe;
	@Schema(description = "权重")
	private int weight;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(long templateId) {
		this.templateId = templateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AttrType getType() {
		return type;
	}

	public void setType(AttrType type) {
		this.type = type;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
