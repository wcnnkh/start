package scw.integration.bytedance;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import scw.codec.support.AES;
import scw.codec.support.Base64;
import scw.codec.support.CharsetCodec;
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
import scw.integration.bytedance.user.FansCheckRequest;
import scw.integration.bytedance.user.FansCheckResponse;
import scw.integration.bytedance.user.FansListResponse;
import scw.integration.bytedance.user.FollowingListResponse;
import scw.integration.bytedance.user.UserPagingRequest;
import scw.integration.bytedance.user.UserRequest;
import scw.integration.bytedance.user.UserinfoResponse;
import scw.integration.bytedance.video.CreateResponse;
import scw.integration.bytedance.video.ImageCreateRequest;
import scw.integration.bytedance.video.ImageUploadResponse;
import scw.integration.bytedance.video.PoiSearchKeywordRequest;
import scw.integration.bytedance.video.PoiSearchKeywordResponse;
import scw.integration.bytedance.video.ShareIdRequest;
import scw.integration.bytedance.video.ShareIdResponse;
import scw.integration.bytedance.video.VdeoPartUploadRequest;
import scw.integration.bytedance.video.VideoCreateRequest;
import scw.integration.bytedance.video.VideoDataResponse;
import scw.integration.bytedance.video.VideoListResponse;
import scw.integration.bytedance.video.VideoPartInitResponse;
import scw.integration.bytedance.video.VideoPartRequest;
import scw.integration.bytedance.video.VideoUploadResponse;
import scw.io.Resource;
import scw.mapper.FieldFeature;
import scw.mapper.MapperUtils;
import scw.net.uri.UriUtils;
import scw.validation.FastValidator;
import scw.validation.ValidationUtils;

public class OpenApi {

	private <R> Map<String, Object> validateAndGetParameterMap(R request) {
		ValidationUtils.validate(() -> FastValidator.getValidator().validate(request));
		return MapperUtils.getMapper().getFields(OauthAccessTokenRequest.class).accept(FieldFeature.IGNORE_STATIC)
				.getValueMap(request);
	}

	private TypeDescriptor wrapperResponseType(ResolvableType resposeType) {
		ResolvableType resolvableType = ResolvableType.forClassWithGenerics(Response.class, resposeType);
		return TypeDescriptor.valueOf(resolvableType);
	}

	private <P> Response<P> doPost(GateWay gateWay, String path, Object urlRequest, Object bodyRequest,
			MediaType mediaType, ResolvableType responseType) {
		String pathToUse = path;
		if (urlRequest != null) {
			Map<String, Object> parameterMap = validateAndGetParameterMap(urlRequest);
			pathToUse = UriUtils.appendQueryParams(pathToUse, parameterMap, URLCodec.UTF_8);
		}

		if (bodyRequest != null) {
			ValidationUtils.validate(() -> FastValidator.getValidator().validate(bodyRequest));
		}

		HttpResponseEntity<Response<P>> responseEntity = HttpUtils.getHttpClient()
				.post(wrapperResponseType(responseType), gateWay.getUrl() + pathToUse, bodyRequest, mediaType);
		return responseEntity.getBody();
	}

	private <P> Response<P> doGet(GateWay gateWay, String path, Object request, ResolvableType responseType) {
		Map<String, Object> parameterMap = validateAndGetParameterMap(request);
		String url = UriUtils.appendQueryParams(gateWay.getUrl() + path, parameterMap, URLCodec.UTF_8);
		HttpResponseEntity<Response<P>> responseEntity = HttpUtils.getHttpClient()
				.get(wrapperResponseType(responseType), url);
		return responseEntity.getBody();
	}

	public Response<OauthAccessTokenResponse> oauthAccessToken(GateWay gateWay, OauthAccessTokenRequest request) {
		return doGet(gateWay, "/oauth/access_token/", request, ResolvableType.forClass(OauthAccessTokenResponse.class));
	}

	public Response<OauthRenewRefreshTokenResponse> oauthRenewRefreshToken(GateWay gateWay,
			OauthRenewRefreshTokenRequest request) {
		return doPost(gateWay, "/oauth/renew_refresh_token/", null, request, MediaType.MULTIPART_FORM_DATA,
				ResolvableType.forClass(OauthRenewRefreshTokenResponse.class));
	}

	public Response<OauthClientTokenResponse> oauthClientToken(GateWay gateWay, OauthClientTokenRequest request) {
		return doGet(gateWay, "/oauth/client_token/", request, ResolvableType.forClass(OauthClientTokenResponse.class));
	}

	public Response<OauthAccessTokenResponse> oauthRefreshToken(GateWay gateWay, OauthRefreshTokenRequest request) {
		return doGet(gateWay, "/oauth/refresh_token/", request,
				ResolvableType.forClass(OauthAccessTokenResponse.class));
	}

	public Response<UserinfoResponse> oauthUserinfo(GateWay gateWay, UserRequest request) {
		return doGet(gateWay, "/oauth/userinfo/", request, ResolvableType.forClass(UserinfoResponse.class));
	}

	public Response<FansListResponse> fansList(GateWay gateWay, UserPagingRequest request) {
		return doGet(gateWay, "/fans/list/", request, ResolvableType.forClass(FansListResponse.class));
	}

	public Response<FollowingListResponse> followingList(GateWay gateWay, UserPagingRequest request) {
		return doGet(gateWay, "/following/list/", request, ResolvableType.forClass(FollowingListResponse.class));
	}

	public Response<FansCheckResponse> fansCheck(GateWay gateWay, FansCheckRequest request) {
		return doGet(gateWay, "/fans/check/", request, ResolvableType.forClass(FansCheckResponse.class));
	}

	public String decryptMobile(String clientSecret, String encryptedMobile) {
		byte[] bytes = clientSecret.getBytes();
		return CharsetCodec.DEFAULT.to(new AES(bytes, bytes)).to(Base64.DEFAULT).decode(encryptedMobile);
	}

	public Response<VideoUploadResponse> videoUpload(UserRequest request, Resource video) {
		return doPost(GateWay.DOUYIN, "/video/upload/", request, Collections.singletonMap("video", video),
				MediaType.MULTIPART_FORM_DATA, ResolvableType.forClass(VideoUploadResponse.class));
	}

	public Response<VideoPartInitResponse> videoPartInit(UserRequest request) {
		return doPost(GateWay.DOUYIN, "/video/part/init/", null, request, MediaType.APPLICATION_JSON,
				ResolvableType.forClass(VideoPartInitResponse.class));
	}

	public Response<ResponseSubCode> videoPartUpload(VdeoPartUploadRequest request, Resource resource) {
		return doPost(GateWay.DOUYIN, "/video/part/upload/", request, Collections.singletonMap("video", resource),
				MediaType.MULTIPART_FORM_DATA, ResolvableType.forClass(ResponseSubCode.class));
	}

	public Response<VideoUploadResponse> videoPartComplete(VideoPartRequest request) {
		return doPost(GateWay.DOUYIN, "/video/part/complete/", null, request, MediaType.APPLICATION_JSON,
				ResolvableType.forClass(VideoUploadResponse.class));
	}

	public Response<CreateResponse> videoCreate(UserRequest user, VideoCreateRequest request) {
		return doPost(GateWay.DOUYIN, "/video/create/", user, request, MediaType.APPLICATION_JSON,
				ResolvableType.forClass(CreateResponse.class));
	}

	public Response<ImageUploadResponse> imageUpload(UserRequest request, Resource image) {
		return doPost(GateWay.DOUYIN, "/image/upload/", request, Collections.singletonMap("image", image),
				MediaType.MULTIPART_FORM_DATA, ResolvableType.forClass(ImageUploadResponse.class));
	}

	public Response<CreateResponse> imageCreate(UserRequest user, ImageCreateRequest request) {
		return doPost(GateWay.DOUYIN, "/image/create/", user, request, MediaType.APPLICATION_JSON,
				ResolvableType.forClass(CreateResponse.class));
	}

	public Response<ResponseCode> deleteVideo(UserRequest user, String itemId) {
		return doPost(GateWay.DOUYIN, "/video/delete/", user, Collections.singletonMap("item_id", itemId),
				MediaType.APPLICATION_JSON, ResolvableType.forClass(ResponseCode.class));
	}

	public Response<VideoListResponse> videoList(UserPagingRequest request) {
		return doGet(GateWay.DOUYIN, "/video/list/", request, ResolvableType.forClass(VideoListResponse.class));
	}

	public Response<VideoDataResponse> videoData(UserRequest user, Collection<String> item_ids) {
		return doPost(GateWay.DOUYIN, "/video/data/", user, Collections.singletonMap("item_ids", item_ids),
				MediaType.APPLICATION_JSON, ResolvableType.forClass(VideoDataResponse.class));
	}

	public Response<ShareIdResponse> shareId(ShareIdRequest request) {
		return doGet(GateWay.DOUYIN, "/share-id/", request, ResolvableType.forClass(ShareIdResponse.class));
	}

	public Response<PoiSearchKeywordResponse> poiSearchKeyword(PoiSearchKeywordRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/search/keyword/", request,
				ResolvableType.forClass(PoiSearchKeywordResponse.class));
	}
}
