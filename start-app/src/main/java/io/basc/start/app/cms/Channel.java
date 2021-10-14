package io.basc.start.app.cms;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.basc.framework.orm.annotation.Entity;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "渠道")
public class Channel implements Serializable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Schema(description = "渠道id")
	private long id;
	@Schema(description = "父级渠道id", required = true, defaultValue = "0")
	@NotNull
	private long parentId;
	@Schema(description = "渠道名称", required = true)
	@NotEmpty
	private String name;
	@Schema(description = "模板id", required = true)
	@NotNull
	private Long templateId;
	@Schema(description = "权重", required = true)
	private int weight;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
