package scw.integration.bytedance.oauth;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.ResponseData;

public class OauthAccessTokenResponse extends ResponseData {
	private static final long serialVersionUID = 1L;
	@Schema(description = "授权用户唯一标识", example = "aaa-bbb-ccc")
	private String open_id;
	@Schema(description = "refresh_token凭证超时时间，单位（秒)", example = "86400")
	private Long refresh_expires_in;
	@Schema(description = "用户刷新access_token", example = "refresh_token")
	private String refresh_token;
	@Schema(description = "用户授权的作用域(Scope)，使用逗号（,）分隔，开放平台几乎几乎每个接口都需要特定的Scope。", example = "user_info")
	private String scope;
	@Schema(description = "接口调用凭证", example = "access_token")
	private String access_token;
	@Schema(description = "access_token接口调用凭证超时时间，单位（秒)", example = "86400")
	private Long expires_in;

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public Long getRefresh_expires_in() {
		return refresh_expires_in;
	}

	public void setRefresh_expires_in(Long refresh_expires_in) {
		this.refresh_expires_in = refresh_expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}
}
