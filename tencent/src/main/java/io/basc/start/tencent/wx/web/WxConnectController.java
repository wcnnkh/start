package io.basc.start.tencent.wx.web;

import io.basc.framework.beans.annotation.Value;
import io.basc.framework.beans.ioc.value.ResourceValueProcessor;
import io.basc.framework.codec.support.URLCodec;
import io.basc.framework.context.annotation.EnableCondition;
import io.basc.framework.http.MediaType;
import io.basc.framework.net.uri.UriUtils;
import io.basc.framework.web.ServerHttpRequest;
import io.basc.framework.web.ServerHttpResponse;
import io.basc.start.tencent.wx.WeiXinUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

/**
 * 辅助类
 * 
 * @author shuchaowen
 *
 */
@Tag(name = "微信公众号授权接口")
@Path(WxConnectController.PATH)
@EnableCondition(condition = "io.basc.start.tencent.wx.connect")
public class WxConnectController {
	static final String PATH = "/io/basc/start/tencent/wx/connect";

	@Value(value = "classpath:/io/basc/start/tencent/wx-connect-call.html", processor = ResourceValueProcessor.class, listener = false)
	private String html;

	@Path("/call/{url}")
	@GET
	public String call(@PathParam("url") String url, String code, String state,
			@Parameter(hidden = true) ServerHttpRequest request,
			@Parameter(hidden = true) ServerHttpResponse response) {
		response.setContentType(MediaType.TEXT_HTML);
		Map<String, String> map = new HashMap<String, String>(4);
		map.put("code", code);
		map.put("state", state);
		String redirectUri = UriUtils.appendQueryParams(url, map, new URLCodec(request.getCharacterEncoding()));
		return html.replace("#{url}", redirectUri);
	}
	
	private String toCallUrl(ServerHttpRequest request, String host, String redirectUri) throws UnsupportedEncodingException{
		return host + PATH + "/call/" + URLEncoder.encode(URLEncoder.encode(redirectUri, request.getCharacterEncoding()), request.getCharacterEncoding());
	}

	@Operation(description = "授权登录")
	@Path("/oauth2/authorize")
	@GET
	@ApiResponse(responseCode = "302")
	public void oauth2Authorize(@Parameter(hidden = true) ServerHttpRequest request,
			@Parameter(hidden = true) ServerHttpResponse serverResponse,
			@Parameter(description = "服务主机的地址", required = true) @QueryParam("host") @NotEmpty String host,
			@Parameter(description = "微信appid", required = true) @QueryParam("appid") @NotEmpty String appid,
			@Parameter(description = "授权方式", example = "snsapi_base") @QueryParam("scope") @NotEmpty String scope,
			@Parameter(description = "回调地址", required = true) @QueryParam("redirectUri") @NotEmpty String redirectUri,
			@Parameter(description = "回调参数") @QueryParam("state") String state) throws IOException {
		String callUrl = toCallUrl(request, host, redirectUri);
		String url = WeiXinUtils.authorizeUlr(appid, callUrl, scope, state);
		serverResponse.sendRedirect(url);
	}

	@Operation(description = "扫码登录")
	@Path("/qrconnect")
	@GET
	@ApiResponse(responseCode = "302")
	public void qrconnect(@Parameter(hidden = true) ServerHttpRequest request,
			@Parameter(hidden = true) ServerHttpResponse serverResponse,
			@Parameter(description = "服务主机的地址", required = true) @QueryParam("host") @NotEmpty String host,
			@Parameter(description = "微信appid", required = true) @QueryParam("appid") @NotEmpty String appid,
			@Parameter(description = "授权方式", required = true, example = "snsapi_login") @QueryParam("scope") @NotEmpty String scope,
			@Parameter(description = "回调地址", required = true) @QueryParam("redirectUri") @NotEmpty String redirectUri,
			@Parameter(description = "回调参数") @QueryParam("state") String state) throws IOException {
		String callUrl = toCallUrl(request, host, redirectUri);
		String url = WeiXinUtils.qrcodeAuthorizeUrl(appid, callUrl, scope, state);
		serverResponse.sendRedirect(url);
	}
}
