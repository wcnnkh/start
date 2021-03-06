package io.basc.start.bytedance.data;

import javax.validation.constraints.NotNull;

import io.basc.start.bytedance.user.UserRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalRequest extends UserRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "	近7/15天；输入7代表7天、15代表15天、30代表30天", required = true)
	@NotNull
	private Long date_type;

	public Long getDate_type() {
		return date_type;
	}

	public void setDate_type(Long date_type) {
		this.date_type = date_type;
	}

}
