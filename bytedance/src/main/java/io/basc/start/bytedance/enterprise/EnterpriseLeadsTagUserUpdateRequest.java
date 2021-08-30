package io.basc.start.bytedance.enterprise;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class EnterpriseLeadsTagUserUpdateRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "是否绑定", required = true)
	@NotNull
	private Boolean bind;
	@Schema(description = "标签id", required = true)
	@NotNull
	private Long tag_id;
	@Schema(description = "用户openid", required = true)
	@NotNull
	private String user_id;

	public Boolean getBind() {
		return bind;
	}

	public void setBind(Boolean bind) {
		this.bind = bind;
	}

	public Long getTag_id() {
		return tag_id;
	}

	public void setTag_id(Long tag_id) {
		this.tag_id = tag_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
