package scw.integration.bytedance.video;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class PoiSearchKeywordRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "调用/oauth/client_token/生成的token，此token不需要用户授权。", example = "clt.943da17996fb5cebfbc70c044c3fc25a57T54DcjT6HNKGqnUdxzy1KcxFnZ", required = true)
	@NotNull
	private String access_token;
	@Schema(description = "分页游标, 第一页请求cursor是0, response中会返回下一页请求用到的cursor, 同时response还会返回has_more来表明是否有更多的数据", example = "0")
	private Long cursor;
	@Schema(description = "每页数量", example = "10", required = true)
	private Long count;
	@Schema(description = "查询关键字，例如美食", required = true)
	private String keyword;
	@Schema(description = "查询城市，例如上海、北京")
	private String city;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Long getCursor() {
		return cursor;
	}

	public void setCursor(Long cursor) {
		this.cursor = cursor;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
