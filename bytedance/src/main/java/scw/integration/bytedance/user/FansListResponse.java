package scw.integration.bytedance.user;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.PagingResponse;

public class FansListResponse extends PagingResponse {
	private static final long serialVersionUID = 1L;
	@Schema(description = "粉丝总数", example = "100")
	private Integer total;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
}
