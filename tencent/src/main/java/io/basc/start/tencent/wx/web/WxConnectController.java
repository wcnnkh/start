package io.basc.start.tencent.wx.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import io.basc.framework.web.ServerHttpRequest;
import io.basc.framework.web.ServerHttpResponse;
import io.basc.start.tencent.wx.WeiXinUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * 辅助类
 * 
 * @author shuchaowen
 *
 */
@Tag(name = "微信公众号辅助接口")
@Path(WxConnectController.PATH)
public class WxConnectController {
	static final String PATH = "/io/basc/start/tencel/wx/connect";

	@Path("/call/{url}")
	public void call(@PathParam("url") String url, String code, String state) {
		System.out.println(url);
		System.out.println(code);
		System.out.println(state);
	}

	@Operation(description = "授权登录(请不要在生产环境使用，仅为了方便测试)")
	@Path("/oauth2/authorize")
	@GET
	@ApiResponse(responseCode = "302")
	public void oauth2Authorize(@Parameter(hidden = true) ServerHttpRequest serverRequest,
			@Parameter(hidden = true) ServerHttpResponse serverResponse,
			@Schema(description = "服务主机的地址", required = true) @QueryParam("host") @NotEmpty String host,
			@Schema(description = "微信appid", required = true) @QueryParam("appid") @NotEmpty String appid,
			@Schema(description = "授权方式", required = true, defaultValue = "snsapi_base") @QueryParam("scope") @NotEmpty String scope,
			@Schema(description = "回调地址", required = true) @QueryParam("redirectUri") @NotEmpty String redirectUri,
			@Schema(description = "回调参数") @QueryParam("state") String state) throws IOException {
		String callUrl = host + PATH + "/call" + URLEncoder.encode(redirectUri, serverRequest.getCharacterEncoding());
		String url = WeiXinUtils.authorizeUlr(appid, callUrl, scope, state);
		serverResponse.sendRedirect(url);
	}
}
