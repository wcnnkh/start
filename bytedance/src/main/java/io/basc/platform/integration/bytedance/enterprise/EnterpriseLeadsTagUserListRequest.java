package io.basc.platform.integration.bytedance.enterprise;

import javax.validation.constraints.NotNull;

import io.basc.platform.integration.bytedance.user.UserPagingRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class EnterpriseLeadsTagUserListRequest extends UserPagingRequest {
	private static final long serialVersionUID = 1L;
	@Schema(required = true)
	@NotNull
	private Long tag_id;

	public Long getTag_id() {
		return tag_id;
	}

	public void setTag_id(Long tag_id) {
		this.tag_id = tag_id;
	}
}
