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
import scw.integration.bytedance.comment.CommentApiType;
import scw.integration.bytedance.comment.CommentListRequest;
import scw.integration.bytedance.comment.CommentListResponse;
import scw.integration.bytedance.comment.CommentReplyListRequest;
import scw.integration.bytedance.comment.CommentReplyRequest;
import scw.integration.bytedance.comment.CommentReplyResponse;
import scw.integration.bytedance.comment.EnterpriseImMessageSendRequest;
import scw.integration.bytedance.comment.EnterpriseImMessageSendResponse;
import scw.integration.bytedance.data.DataExternFansComment;
import scw.integration.bytedance.data.DataExternFansFavourite;
import scw.integration.bytedance.data.DataExternFansSource;
import scw.integration.bytedance.data.DataExternalItemBaseRequest;
import scw.integration.bytedance.data.DataExternalItemBaseResponse;
import scw.integration.bytedance.data.DataExternalItemComment;
import scw.integration.bytedance.data.DataExternalItemLike;
import scw.integration.bytedance.data.DataExternalItemPlay;
import scw.integration.bytedance.data.DataExternalItemRequest;
import scw.integration.bytedance.data.DataExternalItemShare;
import scw.integration.bytedance.data.DataExternalUserComment;
import scw.integration.bytedance.data.DataExternalUserFans;
import scw.integration.bytedance.data.DataExternalUserItem;
import scw.integration.bytedance.data.DataExternalUserLike;
import scw.integration.bytedance.data.DataExternalUserProfile;
import scw.integration.bytedance.data.DataExternalUserRequest;
import scw.integration.bytedance.data.DataExternalUserResponse;
import scw.integration.bytedance.data.DataExternalUserShare;
import scw.integration.bytedance.data.FansDataResponse;
import scw.integration.bytedance.data.HotsearchSentencesResponse;
import scw.integration.bytedance.data.HotsearchTrendingSentences;
import scw.integration.bytedance.data.HotsearchVideosRequest;
import scw.integration.bytedance.data.StarAuthorScore;
import scw.integration.bytedance.data.StarAuthorScoreV2Request;
import scw.integration.bytedance.data.StarAuthorScoreV2Response;
import scw.integration.bytedance.data.StarHotListRequest;
import scw.integration.bytedance.data.StarHotListResponse;
import scw.integration.bytedance.oauth.ClientPagingRequest;
import scw.integration.bytedance.oauth.ClientRequest;
import scw.integration.bytedance.oauth.OauthAccessTokenRequest;
import scw.integration.bytedance.oauth.OauthAccessTokenResponse;
import scw.integration.bytedance.oauth.OauthClientTokenRequest;
import scw.integration.bytedance.oauth.OauthClientTokenResponse;
import scw.integration.bytedance.oauth.OauthRefreshTokenRequest;
import scw.integration.bytedance.oauth.OauthRenewRefreshTokenRequest;
import scw.integration.bytedance.oauth.OauthRenewRefreshTokenResponse;
import scw.integration.bytedance.search.VideoSearchCommentListRequest;
import scw.integration.bytedance.search.VideoSearchCommentReplyListRequest;
import scw.integration.bytedance.search.VideoSearchCommentReplyRequest;
import scw.integration.bytedance.search.VideoSearchRequest;
import scw.integration.bytedance.search.VideoSearchResponse;
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
import scw.integration.bytedance.video.VideoData;
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

	private <P> Response<P> doPost(GateWay gateWay, String path, Object urlRequest, Object bodyRequest,
			MediaType mediaType, Class<? extends P> responseType) {
		return doPost(gateWay, path, urlRequest, bodyRequest, mediaType, ResolvableType.forClass(responseType));
	}

	private <P> Response<P> doGet(GateWay gateWay, String path, Object request, ResolvableType responseType) {
		Map<String, Object> parameterMap = validateAndGetParameterMap(request);
		String url = UriUtils.appendQueryParams(gateWay.getUrl() + path, parameterMap, URLCodec.UTF_8);
		HttpResponseEntity<Response<P>> responseEntity = HttpUtils.getHttpClient()
				.get(wrapperResponseType(responseType), url);
		return responseEntity.getBody();
	}

	private <P> Response<P> doGet(GateWay gateWay, String path, Object request, Class<? extends P> responseType) {
		return doGet(gateWay, path, request, ResolvableType.forClass(responseType));
	}

	public Response<OauthAccessTokenResponse> oauthAccessToken(GateWay gateWay, OauthAccessTokenRequest request) {
		return doGet(gateWay, "/oauth/access_token/", request, OauthAccessTokenResponse.class);
	}

	public Response<OauthRenewRefreshTokenResponse> oauthRenewRefreshToken(GateWay gateWay,
			OauthRenewRefreshTokenRequest request) {
		return doPost(gateWay, "/oauth/renew_refresh_token/", null, request, MediaType.MULTIPART_FORM_DATA,
				OauthRenewRefreshTokenResponse.class);
	}

	public Response<OauthClientTokenResponse> oauthClientToken(GateWay gateWay, OauthClientTokenRequest request) {
		return doGet(gateWay, "/oauth/client_token/", request, OauthClientTokenResponse.class);
	}

	public Response<OauthAccessTokenResponse> oauthRefreshToken(GateWay gateWay, OauthRefreshTokenRequest request) {
		return doGet(gateWay, "/oauth/refresh_token/", request, OauthAccessTokenResponse.class);
	}

	public Response<UserinfoResponse> oauthUserinfo(GateWay gateWay, UserRequest request) {
		return doGet(gateWay, "/oauth/userinfo/", request, UserinfoResponse.class);
	}

	public Response<FansListResponse> fansList(GateWay gateWay, UserPagingRequest request) {
		return doGet(gateWay, "/fans/list/", request, FansListResponse.class);
	}

	public Response<FollowingListResponse> followingList(GateWay gateWay, UserPagingRequest request) {
		return doGet(gateWay, "/following/list/", request, FollowingListResponse.class);
	}

	public Response<FansCheckResponse> fansCheck(GateWay gateWay, FansCheckRequest request) {
		return doGet(gateWay, "/fans/check/", request, FansCheckResponse.class);
	}

	public String decryptMobile(String clientSecret, String encryptedMobile) {
		byte[] bytes = clientSecret.getBytes();
		return CharsetCodec.DEFAULT.to(new AES(bytes, bytes)).to(Base64.DEFAULT).decode(encryptedMobile);
	}

	public Response<VideoUploadResponse> videoUpload(GateWay gateWay, UserRequest request, Resource video) {
		return doPost(gateWay, gateWay.getVideoContextPath() + "/video/upload/", request,
				Collections.singletonMap("video", video), MediaType.MULTIPART_FORM_DATA, VideoUploadResponse.class);
	}

	public Response<VideoPartInitResponse> videoPartInit(GateWay gateWay, UserRequest request) {
		return doPost(gateWay, gateWay.getVideoContextPath() + "/video/part/init/", request, null,
				MediaType.APPLICATION_JSON, VideoPartInitResponse.class);
	}

	public Response<ResponseSubCode> videoPartUpload(GateWay gateWay, VdeoPartUploadRequest request,
			Resource resource) {
		return doPost(gateWay, gateWay.getVideoContextPath() + "/video/part/upload/", request,
				Collections.singletonMap("video", resource), MediaType.MULTIPART_FORM_DATA, ResponseSubCode.class);
	}

	public Response<VideoUploadResponse> videoPartComplete(GateWay gateWay, VideoPartRequest request) {
		return doPost(gateWay, gateWay.getVideoContextPath() + "/video/part/complete/", request, null,
				MediaType.APPLICATION_JSON, VideoUploadResponse.class);
	}

	public Response<CreateResponse> videoCreate(UserRequest user, VideoCreateRequest request) {
		return doPost(GateWay.DOUYIN, "/video/create/", user, request, MediaType.APPLICATION_JSON,
				CreateResponse.class);
	}

	public Response<ImageUploadResponse> imageUpload(UserRequest request, Resource image) {
		return doPost(GateWay.DOUYIN, "/image/upload/", request, Collections.singletonMap("image", image),
				MediaType.MULTIPART_FORM_DATA, ImageUploadResponse.class);
	}

	public Response<CreateResponse> imageCreate(UserRequest user, ImageCreateRequest request) {
		return doPost(GateWay.DOUYIN, "/image/create/", user, request, MediaType.APPLICATION_JSON,
				CreateResponse.class);
	}

	public Response<ResponseCode> deleteVideo(UserRequest user, String itemId) {
		return doPost(GateWay.DOUYIN, "/video/delete/", user, Collections.singletonMap("item_id", itemId),
				MediaType.APPLICATION_JSON, ResponseCode.class);
	}

	public Response<VideoListResponse> videoList(GateWay gateWay, UserPagingRequest request) {
		return doGet(gateWay, gateWay.getVideoContextPath() + "/video/list/", request, VideoListResponse.class);
	}

	public Response<VideoDataResponse> videoData(GateWay gateWay, UserRequest user, Collection<String> item_ids) {
		return doPost(gateWay, gateWay.getVideoContextPath() + "/video/data/", user,
				Collections.singletonMap("item_ids", item_ids), MediaType.APPLICATION_JSON, VideoDataResponse.class);
	}

	public Response<ShareIdResponse> shareId(ShareIdRequest request) {
		return doGet(GateWay.DOUYIN, "/share-id/", request, ShareIdResponse.class);
	}

	public Response<PoiSearchKeywordResponse> poiSearchKeyword(PoiSearchKeywordRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/search/keyword/", request, PoiSearchKeywordResponse.class);
	}

	public Response<CommentListResponse> commentList(CommentApiType apiType, CommentListRequest request) {
		return doGet(GateWay.DOUYIN, apiType.getContextPath() + "/comment/list/", request, CommentListResponse.class);
	}

	public Response<CommentListResponse> commentReplyList(CommentApiType apiType, CommentReplyListRequest reqeust) {
		return doGet(GateWay.DOUYIN, apiType.getContextPath() + "/comment/reply/list/", reqeust,
				CommentListResponse.class);
	}

	public Response<CommentReplyResponse> commentReply(CommentApiType apiType, UserRequest user,
			CommentReplyRequest request) {
		return doPost(GateWay.DOUYIN, apiType.getContextPath() + "/comment/reply/", user, request,
				MediaType.APPLICATION_JSON, CommentReplyResponse.class);
	}

	public Response<EnterpriseImMessageSendResponse> enterpriseImMessageSend(UserRequest user,
			EnterpriseImMessageSendRequest request) {
		return doPost(GateWay.DOUYIN, "/enterprise/im/message/send/", user, request, MediaType.APPLICATION_JSON,
				EnterpriseImMessageSendResponse.class);
	}

	public Response<VideoSearchResponse> videoSearch(VideoSearchRequest request) {
		return doGet(GateWay.DOUYIN, "/video/search/", request, VideoSearchResponse.class);
	}

	public Response<CommentListResponse> videoSearchCommentList(VideoSearchCommentListRequest request) {
		return doGet(GateWay.DOUYIN, "/video/search/comment/list/", request, CommentListResponse.class);
	}

	public Response<CommentReplyResponse> videoSearchCommentReply(UserRequest user,
			VideoSearchCommentReplyRequest request) {
		return doPost(GateWay.DOUYIN, "/video/search/comment/reply/", user, request, MediaType.APPLICATION_JSON,
				CommentReplyResponse.class);
	}

	public Response<CommentListResponse> videoSearchCommentReplyList(VideoSearchCommentReplyListRequest request) {
		return doGet(GateWay.DOUYIN, "/video/search/comment/reply/list/", request, CommentListResponse.class);
	}

	public Response<DataExternalUserResponse<DataExternalUserItem>> dataExternalUserItem(
			DataExternalUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/user/item/", request,
				ResolvableType.forClassWithGenerics(DataExternalUserResponse.class, DataExternalUserItem.class));
	}

	public Response<DataExternalUserResponse<DataExternalUserFans>> dataExternalUserFans(
			DataExternalUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/user/fans/", request,
				ResolvableType.forClassWithGenerics(DataExternalUserResponse.class, DataExternalUserItem.class));
	}

	public Response<DataExternalUserResponse<DataExternalUserLike>> dataExternalUserLike(
			DataExternalUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/user/like/", request,
				ResolvableType.forClassWithGenerics(DataExternalUserResponse.class, DataExternalUserLike.class));
	}

	public Response<DataExternalUserResponse<DataExternalUserComment>> dataExternalUserComment(
			DataExternalUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/user/comment/", request,
				ResolvableType.forClassWithGenerics(DataExternalUserResponse.class, DataExternalUserComment.class));
	}

	public Response<DataExternalUserResponse<DataExternalUserShare>> dataExternalUserShare(
			DataExternalUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/user/share/", request,
				ResolvableType.forClassWithGenerics(DataExternalUserResponse.class, DataExternalUserShare.class));
	}

	public Response<DataExternalUserResponse<DataExternalUserProfile>> dataExternalUserProfile(
			DataExternalUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/user/profile/", request,
				ResolvableType.forClassWithGenerics(DataExternalUserResponse.class, DataExternalUserProfile.class));
	}

	public Response<DataExternalItemBaseResponse> dataExternalItemBase(DataExternalItemBaseRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/item/base/", request, DataExternalItemBaseResponse.class);
	}

	public Response<DataExternalUserResponse<DataExternalItemLike>> dataExternalItemLike(
			DataExternalItemRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/item/like/", request,
				ResolvableType.forClassWithGenerics(DataExternalUserResponse.class, DataExternalItemLike.class));
	}

	public Response<DataExternalUserResponse<DataExternalItemComment>> dataExternalItemComment(
			DataExternalItemRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/item/comment/", request,
				ResolvableType.forClassWithGenerics(DataExternalUserResponse.class, DataExternalItemComment.class));
	}

	public Response<DataExternalUserResponse<DataExternalItemPlay>> dataExternalItemPlay(
			DataExternalItemRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/item/play/", request,
				ResolvableType.forClassWithGenerics(DataExternalUserResponse.class, DataExternalItemPlay.class));
	}

	public Response<DataExternalUserResponse<DataExternalItemShare>> dataExternalItemShare(
			DataExternalItemRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/item/share/", request,
				ResolvableType.forClassWithGenerics(DataExternalUserResponse.class, DataExternalItemShare.class));
	}

	public Response<FansDataResponse> fansData(UserRequest request) {
		return doGet(GateWay.DOUYIN, "/fans/data/", request, FansDataResponse.class);
	}

	public Response<ListData<DataExternFansSource>> dataExternFansSource(UserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/fans/source/", request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternFansSource.class));
	}

	public Response<ListData<DataExternFansFavourite>> dataExternFansFavourite(UserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/fans/favourite/", request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternFansFavourite.class));
	}

	public Response<ListData<DataExternFansComment>> dataExternFansComment(UserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/fans/comment/", request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternFansComment.class));
	}

	public Response<HotsearchSentencesResponse> hotsearchSentences(ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/hotsearch/sentences/", request, HotsearchSentencesResponse.class);
	}

	public Response<PagingResponseList<HotsearchTrendingSentences>> hotsearchTrendingSentences(
			ClientPagingRequest request) {
		return doGet(GateWay.DOUYIN, "/hotsearch/trending/sentences/", request,
				ResolvableType.forClassWithGenerics(PagingResponseList.class, HotsearchTrendingSentences.class));
	}

	public Response<ResponseCodeList<VideoData>> hotsearchVideos(HotsearchVideosRequest request) {
		return doGet(GateWay.DOUYIN, "/hotsearch/videos/", request,
				ResolvableType.forClassWithGenerics(ResponseCodeList.class, VideoData.class));
	}

	public Response<StarHotListResponse> starHotList(StarHotListRequest request) {
		return doGet(GateWay.DOUYIN, "/star/hot_list/", request, StarHotListResponse.class);
	}

	public Response<StarAuthorScore> starAuthorScore(UserRequest request) {
		return doGet(GateWay.DOUYIN, "/star/author_score/", request, StarAuthorScore.class);
	}

	public Response<StarAuthorScoreV2Response> starAuthorScoreV2(StarAuthorScoreV2Request request) {
		return doGet(GateWay.DOUYIN, "/star/author_score_v2/", request, StarAuthorScoreV2Response.class);
	}
}
