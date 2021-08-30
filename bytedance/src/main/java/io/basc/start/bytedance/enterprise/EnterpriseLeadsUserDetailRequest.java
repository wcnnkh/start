package io.basc.start.bytedance.enterprise;

import javax.validation.constraints.NotNull;

import io.basc.start.bytedance.user.UserRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class EnterpriseLeadsUserDetailRequest extends UserRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "意向用户的open_id，可从意向用户列表中获取到。", example = "aw213642-2845-40bc-9bdf-9a1564b94059", required = true)
	@NotNull
	private String user_id;
}
