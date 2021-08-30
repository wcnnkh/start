package io.basc.start.bytedance.data;

import javax.validation.constraints.NotNull;

import io.basc.start.bytedance.oauth.ClientRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class StarAuthorScoreV2Request extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "达人抖音号", example = "123", required = true)
	@NotNull
	private String unique_id;

	public String getUnique_id() {
		return unique_id;
	}

	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}
}
