package io.basc.start.tencent.wx.open;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import io.basc.framework.net.uri.UriUtils;
import io.swagger.v3.oas.annotations.media.Schema;

public class WxConnect implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String AUTHORIZE_HOST = "https://open.weixin.qq.com/connect/oauth2/authorize";
	public static final String QRCONNECT_HOST = "https://open.weixin.qq.com/connect/qrconnect";

	@Schema(description = "微信的appid", required = true)
	@NotEmpty
	private String appid;
	@Schema(description = "授权作用域", example = "snsapi_base", required = true)
	@NotEmpty
	private String scope;
	@Schema(description = "回调地址", required = true)
	@NotEmpty
	private String redirectUri;
	@Schema(description = "回调时附带的参数，目的是为了安全")
	private String state;
	@Schema(description = "返回类型, 固定值code", example = "code", defaultValue = "code", required = true)
	@NotEmpty
	private String responseType = "code";

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	@Override
	public String toString() {
		return toQueryUrl();
	}

	public String toQueryUrl() {
		StringBuilder sb = new StringBuilder();
		sb.append("appid=").append(getAppid());
		sb.append("&redirect_uri=").append(UriUtils.encode(getRedirectUri()));
		sb.append("&response_type=").append(getResponseType());
		sb.append("&scope=").append(getScope());
		if (getState() != null) {
			sb.append("&state=").append(getState());
		}
		return sb.toString();
	}

	public String toAuthorizeUrl() {
		return AUTHORIZE_HOST + "?" + toQueryUrl() + "#wechat_redirect";
	}

	public String toQrconnectUrl() {
		return QRCONNECT_HOST + "?" + toQueryUrl() + "#wechat_redirect";
	}
}
