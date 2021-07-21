package scw.integration.bytedance.user;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class UserPagingRequest extends UserRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "分页游标, 第一页请求cursor是0, response中会返回下一页请求用到的cursor, 同时response还会返回has_more来表明是否有更多的数据。", example = "0")
	private Long cursor;
	@Schema(description = "每页数量", example = "10", required = true)
	@NotNull
	private Integer count;
}
