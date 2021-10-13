package io.basc.start.cms;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.basc.framework.orm.annotation.Entity;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "内容")
@Entity
public class Content implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "内容id")
	@PrimaryKey
	private long id;
	@Schema(description = "内容标题", required = true)
	private String title;
	@Schema(description = "渠道id", required = true)
	private Long channelId;
	@Schema(description = "模板id", required = true)
	@NotNull
	private Long templateId;
	@Schema(description = "创建时间")
	private long createTime;
	@Schema(description = "权重")
	private int weight;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getChannelId() {
		return channelId;
	}

	public void setChannelId(Long channelId) {
		this.channelId = channelId;
	}

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
}
