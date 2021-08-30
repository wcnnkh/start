package io.basc.start.bytedance.comment;

import javax.validation.constraints.NotNull;

import io.basc.start.bytedance.user.UserPagingRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class CommentListRequest extends UserPagingRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "视频id", example = "@8hxdhauTCMppanGnM4ltGM780mDqPP+KPpR0qQOmLVAXb/T060zdRmYqig357zEBq6CZRp4NVe6qLIJW/V/x1w==", required = true)
	@NotNull
	private String item_id;
	@Schema(description = "列表排序方式，不传默认按推荐序，可选值：time(时间逆序)、time_asc(时间顺序)", example = "time")
	private String sort_type;

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getSort_type() {
		return sort_type;
	}

	public void setSort_type(String sort_type) {
		this.sort_type = sort_type;
	}
}
