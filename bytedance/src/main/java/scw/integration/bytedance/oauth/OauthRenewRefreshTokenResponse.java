package scw.integration.bytedance.oauth;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.ResponseCode;

public class OauthRenewRefreshTokenResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "过期时间，单位（秒）", example = "86400")
	private Long expires_in;
	@Schema(description = "用户刷新", example = "refresh_token")
	private String refresh_token;

	public Long getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(Long expires_in) {
		this.expires_in = expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
}
