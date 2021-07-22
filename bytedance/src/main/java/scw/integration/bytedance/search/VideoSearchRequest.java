package scw.integration.bytedance.search;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.user.UserPagingRequest;

public class VideoSearchRequest extends UserPagingRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "关键词", required = true)
	@NotNull
	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
}
