package io.basc.start.cms;

import java.io.Serializable;

import io.basc.framework.orm.annotation.Entity;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(description = "模板")
public class Template implements Serializable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Schema(description = "模板id")
	private long id;
	@Schema(description = "模板名称")
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
