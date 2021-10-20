package io.basc.start.tencent.wx.web;

import io.basc.framework.beans.annotation.Value;
import io.basc.framework.beans.ioc.value.ResourceValueProcessor;
import io.basc.framework.codec.support.URLCodec;
import io.basc.framework.context.annotation.EnableCondition;
import io.basc.framework.http.MediaType;
import io.basc.framework.lang.Nullable;
import io.basc.framework.net.uri.UriUtils;
import io.basc.framework.web.ServerHttpRequest;
import io.basc.framework.web.ServerHttpResponse;
import io.basc.start.tencent.wx.WeiXinUtils;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
	static final String DEFAULT_TITLE = "跳转中......";

	@Value(value = "classpath:/io/basc/start/tencent/wx-connect-call.html", processor = ResourceValueProcessor.class, listener = false)
	private String html;
	
	@Value("${io.basc.start.tencent.wx.connect.title:" + DEFAULT_TITLE + "}")
	private String title;

	@Path("/call/{url}")
	@GET
	@Hidden
	public String call(@PathParam("url") @NotEmpty String url, @NotEmpty String code, String state,
			@Parameter(hidden = true) ServerHttpRequest request,
			@Parameter(hidden = true) ServerHttpResponse response) throws UnsupportedEncodingException {
		response.setContentType(MediaType.TEXT_HTML);
		Map<String, String> map = new HashMap<String, String>(4);
		map.put("code", code);
		map.put("state", state);
		String redirectUri = UriUtils.appendQueryParams(URLDecoder.decode(url, request.getCharacterEncoding()), map, new URLCodec(request.getCharacterEncoding()));
		String text = html.replace("#{url}", redirectUri);
		text = text.replace(DEFAULT_TITLE, title);
		return text;
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
	
	public static String oauth2Authorize(String host, String appid, String redirectUri, @Nullable String scope, @Nullable String state){
		StringBuilder sb = new StringBuilder();
		sb.append(host);
		sb.append(PATH);
		sb.append("/oauth2/authorize");
		sb.append("?host=").append(URLCodec.UTF_8.encode(host));
		sb.append("&appid=").append(appid);
		sb.append("&redirectUri=").append(URLCodec.UTF_8.encode(redirectUri));
		if(scope != null){
			sb.append("&scope=").append(scope);
		}
		
		if(state != null){
			sb.append("&state=").append(state);
		}
		return sb.toString();
	}
	
	public static String qrconnect(String host, String appid, String redirectUri, @Nullable String scope, @Nullable String state){
		StringBuilder sb = new StringBuilder();
		sb.append(host);
		sb.append(PATH);
		sb.append("/qrconnect");
		sb.append("?host=").append(URLCodec.UTF_8.encode(host));
		sb.append("&appid=").append(appid);
		sb.append("&redirectUri=").append(URLCodec.UTF_8.encode(redirectUri));
		if(scope != null){
			sb.append("&scope=").append(scope);
		}
		
		if(state != null){
			sb.append("&state=").append(state);
		}
		return sb.toString();
	}
}
