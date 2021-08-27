package io.basc.platform.integration.bytedance.oauth;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * https://open.douyin.com/platform/doc/6848834666171009035
 * 
 * @author shuchaowen
 *
 */
public class PlatformOauthConnectRequest extends OauthAuthorizeRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "应用授权可选作用域,多个授权作用域以英文逗号（,）分隔，每一个授权作用域后需要加上一个是否默认勾选的参数，1为默认勾选，0为默认不勾选", example = "optionalScope=friend_relation,1,message,0")
	private String optionalScope;

	public String getOptionalScope() {
		return optionalScope;
	}

	public void setOptionalScope(String optionalScope) {
		this.optionalScope = optionalScope;
	}
}
