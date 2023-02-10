package io.basc.start.template.pojo;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AttributeTemplateDescribe implements Serializable {
	private static final long serialVersionUID = 1L;
	private long templateId;
	private String name;
	private String describe;
	private int order;
	private long createTime;
	private long updateTime;
	private String createBy;
	private String updateBy;
	@Schema(description = "是否已被删除")
	private boolean delete;
}
