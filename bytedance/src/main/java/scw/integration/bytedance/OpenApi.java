package scw.integration.bytedance;

import java.util.Map;

import scw.codec.support.URLCodec;
import scw.convert.TypeDescriptor;
import scw.core.ResolvableType;
import scw.http.HttpResponseEntity;
import scw.http.HttpUtils;
import scw.http.MediaType;
import scw.integration.bytedance.oauth.OauthAccessTokenRequest;
import scw.integration.bytedance.oauth.OauthAccessTokenResponse;
import scw.integration.bytedance.oauth.OauthClientTokenRequest;
import scw.integration.bytedance.oauth.OauthClientTokenResponse;
import scw.integration.bytedance.oauth.OauthRefreshTokenRequest;
import scw.integration.bytedance.oauth.OauthRenewRefreshTokenRequest;
import scw.integration.bytedance.oauth.OauthRenewRefreshTokenResponse;
import scw.integration.bytedance.oauth.OauthUserPagingRequest;
import scw.integration.bytedance.oauth.OauthUserRequest;
import scw.integration.bytedance.oauth.OauthUserinfoResponse;
import scw.mapper.FieldFeature;
import scw.mapper.MapperUtils;
import scw.net.uri.UriUtils;
import scw.validation.FastValidator;
import scw.validation.ValidationUtils;

public class OpenApi {
	public static final String DOUYIN_GATEWAY = "https://open.douyin.com/";
	public static final String SNSSDK_GATEWAY = "https://open.snssdk.com/";
	public static final String IXIGUA_GATEWAY = "https://open-api.ixigua.com/";

	private final String gateway;

	public OpenApi(String gateway) {
		this.gateway = gateway;
	}

	public String getGateway() {
		return gateway;
	}

	private <R> Map<String, Object> validateAndGetParameterMap(R request) {
		ValidationUtils.validate(() -> FastValidator.getValidator().validate(request));
		return MapperUtils.getMapper().getFields(OauthAccessTokenRequest.class).accept(FieldFeature.IGNORE_STATIC)
				.getValueMap(request);
	}

	private TypeDescriptor wrapperResponseType(ResolvableType resposeType) {
		ResolvableType resolvableType = ResolvableType.forClassWithGenerics(Response.class, resposeType);
		return TypeDescriptor.valueOf(resolvableType);
	}

	private <R, P> Response<P> get(String path, R request, Class<? extends P> responseType) {
		Map<String, Object> parameterMap = validateAndGetParameterMap(request);
		String url = UriUtils.appendQueryParams(getGateway() + path, parameterMap, URLCodec.UTF_8);
		HttpResponseEntity<Response<P>> responseEntity = HttpUtils.getHttpClient()
				.get(wrapperResponseType(ResolvableType.forClass(responseType)), url);
		return responseEntity.getBody();
	}

	private <R, P> Response<PagingResponseData<P>> paging(String path, R request, Class<? extends P> responseType) {
		Map<String, Object> parameterMap = validateAndGetParameterMap(request);
		String url = UriUtils.appendQueryParams(getGateway() + path, parameterMap, URLCodec.UTF_8);
		HttpResponseEntity<Response<PagingResponseData<P>>> responseEntity = HttpUtils.getHttpClient().get(
				wrapperResponseType(ResolvableType.forClassWithGenerics(PagingResponseData.class, responseType)), url);
		return responseEntity.getBody();
	}

	private <R, P> Response<P> post(String path, R request, Class<? extends P> responseType) {
		Map<String, Object> parameterMap = validateAndGetParameterMap(request);
		HttpResponseEntity<Response<P>> responseEntity = HttpUtils.getHttpClient().post(
				wrapperResponseType(ResolvableType.forClass(OauthRenewRefreshTokenResponse.class)), getGateway() + path,
				parameterMap, MediaType.MULTIPART_FORM_DATA);
		return responseEntity.getBody();
	}

	public Response<OauthAccessTokenResponse> oauthAccessToken(OauthAccessTokenRequest request) {
		return get("/oauth/access_token/", request, OauthAccessTokenResponse.class);
	}

	public Response<OauthRenewRefreshTokenResponse> oauthRenewRefreshToken(OauthRenewRefreshTokenRequest request) {
		return post("/oauth/renew_refresh_token/", request, OauthRenewRefreshTokenResponse.class);
	}

	public Response<OauthClientTokenResponse> oauthClientToken(OauthClientTokenRequest request) {
		return get("/oauth/client_token/", request, OauthClientTokenResponse.class);
	}

	public Response<OauthAccessTokenResponse> oauthRefreshToken(OauthRefreshTokenRequest request) {
		return get("/oauth/refresh_token/", request, OauthAccessTokenResponse.class);
	}

	public Response<OauthUserinfoResponse> oauthUserinfo(OauthUserRequest request) {
		return get("/oauth/userinfo/", request, OauthUserinfoResponse.class);
	}

	public Response<PagingResponseData<OauthUserinfoResponse>> fansList(OauthUserPagingRequest request) {
		return paging("/fans/list/", request, OauthUserinfoResponse.class);
	}

	public Response<PagingResponseData<OauthUserinfoResponse>> followingList(OauthUserPagingRequest request) {
		return paging("/following/list/", request, OauthUserinfoResponse.class);
	}
}
