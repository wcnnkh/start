package scw.integration.bytedance.oauth;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class OauthUserRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "通过/oauth/access_token/获取，用户唯一标志", example = "ba253642-0590-40bc-9bdf-9a1334b94059", required = true)
	@NotNull
	private String open_id;
	@Schema(description = "调用/oauth/access_token/生成的token，此token需要用户授权。", example = "act.1d1021d2aee3d41fee2d2add43456badMFZnrhFhfWotu3Ecuiuka27L56lr", required = true)
	@NotNull
	private String access_token;

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
}
