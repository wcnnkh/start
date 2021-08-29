package io.basc.integration.bytedance.oauth;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class OauthAuthorizeRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "应用唯一标识", required = true)
	@NotNull
	private String client_key;
	@Schema(description = "写死为'code'即可", defaultValue = "code", required = true)
	@NotNull
	private String response_type = "code";
	@Schema(description = "应用授权作用域,多个授权作用域以英文逗号（,）分隔", required = true)
	@NotNull
	private String scope;
	@Schema(description = "授权成功后的回调地址，必须以http/https开头。域名必须对应申请应用时填写的域名，如不清楚请联系应用申请人。", required = true)
	@NotNull
	private String redirect_uri;
	@Schema(description = "用于保持请求和回调的状态")
	private String state;
}
