package scw.integration.bytedance.enterprise;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.ResponseCode;

public class EnterpriseLeadsTagResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "标签id")
	private Long tag_id;

	public Long getTag_id() {
		return tag_id;
	}

	public void setTag_id(Long tag_id) {
		this.tag_id = tag_id;
	}
}
