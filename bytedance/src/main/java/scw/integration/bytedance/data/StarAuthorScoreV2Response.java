package scw.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class StarAuthorScoreV2Response extends StarAuthorScore {
	private static final long serialVersionUID = 1L;
	@Schema(description = "	达人抖音号")
	private String unique_id;

	public String getUnique_id() {
		return unique_id;
	}

	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}
}
