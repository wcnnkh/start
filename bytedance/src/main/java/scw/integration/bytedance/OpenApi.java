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
import scw.integration.bytedance.data.AmusementRankApiType;
import scw.integration.bytedance.data.CarRankApiType;
import scw.integration.bytedance.data.CospaRankApiType;
import scw.integration.bytedance.data.DataExternBillboard;
import scw.integration.bytedance.data.DataExternBillboardHotVideo;
import scw.integration.bytedance.data.DataExternBillboardLive;
import scw.integration.bytedance.data.DataExternBillboardMusic;
import scw.integration.bytedance.data.DataExternBillboardProp;
import scw.integration.bytedance.data.DataExternBillboardStars;
import scw.integration.bytedance.data.DataExternBillboardTopic;
import scw.integration.bytedance.data.DataExternFansComment;
import scw.integration.bytedance.data.DataExternFansFavourite;
import scw.integration.bytedance.data.DataExternFansSource;
import scw.integration.bytedance.data.DataExternalAnchorMpItemClickDistribution;
import scw.integration.bytedance.data.DataExternalAnchorMpItemClickDistributionRequest;
import scw.integration.bytedance.data.DataExternalItemBaseRequest;
import scw.integration.bytedance.data.DataExternalItemBaseResponse;
import scw.integration.bytedance.data.DataExternalItemComment;
import scw.integration.bytedance.data.DataExternalItemLike;
import scw.integration.bytedance.data.DataExternalItemPlay;
import scw.integration.bytedance.data.DataExternalItemRequest;
import scw.integration.bytedance.data.DataExternalItemShare;
import scw.integration.bytedance.data.DataExternalSdkShare;
import scw.integration.bytedance.data.DataExternalUserComment;
import scw.integration.bytedance.data.DataExternalUserFans;
import scw.integration.bytedance.data.DataExternalUserItem;
import scw.integration.bytedance.data.DataExternalUserLike;
import scw.integration.bytedance.data.DataExternalUserProfile;
import scw.integration.bytedance.data.DataExternalUserRequest;
import scw.integration.bytedance.data.DataExternalUserResponse;
import scw.integration.bytedance.data.DataExternalUserShare;
import scw.integration.bytedance.data.DiscoveryEntRankItemRequest;
import scw.integration.bytedance.data.DiscoveryEntRankItemResponse;
import scw.integration.bytedance.data.DiscoveryEntRrankVersion;
import scw.integration.bytedance.data.DiscoveryEntRrankVersionRequest;
import scw.integration.bytedance.data.FansDataResponse;
import scw.integration.bytedance.data.FoodRankApiType;
import scw.integration.bytedance.data.GameRankApiType;
import scw.integration.bytedance.data.HotsearchSentencesResponse;
import scw.integration.bytedance.data.HotsearchTrendingSentences;
import scw.integration.bytedance.data.HotsearchVideosRequest;
import scw.integration.bytedance.data.MusicRankApiType;
import scw.integration.bytedance.data.SportRankApiType;
import scw.integration.bytedance.data.StarAuthorScore;
import scw.integration.bytedance.data.StarAuthorScoreV2Request;
import scw.integration.bytedance.data.StarAuthorScoreV2Response;
import scw.integration.bytedance.data.StarHotListRequest;
import scw.integration.bytedance.data.StarHotListResponse;
import scw.integration.bytedance.data.TravelRankApiType;
import scw.integration.bytedance.devtool.DevtoolMicappIsLegalRequest;
import scw.integration.bytedance.devtool.DevtoolMicappIsLegalResponse;
import scw.integration.bytedance.devtool.JsGetticketResponse;
import scw.integration.bytedance.devtool.SandboxWebhookEventSendRequest;
import scw.integration.bytedance.enterprise.EnterpriseImCardDeleteRequest;
import scw.integration.bytedance.enterprise.EnterpriseImCardListResponse;
import scw.integration.bytedance.enterprise.EnterpriseImCardSaveRequest;
import scw.integration.bytedance.enterprise.EnterpriseImCardSaveResponse;
import scw.integration.bytedance.enterprise.EnterpriseLeadsTagResponse;
import scw.integration.bytedance.enterprise.EnterpriseLeadsTagUserListRequest;
import scw.integration.bytedance.enterprise.EnterpriseLeadsTagUserUpdateRequest;
import scw.integration.bytedance.enterprise.EnterpriseLeadsUserAction;
import scw.integration.bytedance.enterprise.EnterpriseLeadsUserActionListRequest;
import scw.integration.bytedance.enterprise.EnterpriseLeadsUserDetailRequest;
import scw.integration.bytedance.enterprise.EnterpriseLeadsUserDetailResponse;
import scw.integration.bytedance.enterprise.EnterpriseLeadsUserListRequest;
import scw.integration.bytedance.enterprise.EnterpriseLeadsUserListResponse;
import scw.integration.bytedance.enterprise.Tag;
import scw.integration.bytedance.oauth.ClientPagingRequest;
import scw.integration.bytedance.oauth.ClientRequest;
import scw.integration.bytedance.oauth.OauthAccessTokenRequest;
import scw.integration.bytedance.oauth.OauthAccessTokenResponse;
import scw.integration.bytedance.oauth.OauthClientTokenRequest;
import scw.integration.bytedance.oauth.OauthClientTokenResponse;
import scw.integration.bytedance.oauth.OauthRefreshTokenRequest;
import scw.integration.bytedance.oauth.OauthRenewRefreshTokenRequest;
import scw.integration.bytedance.oauth.OauthRenewRefreshTokenResponse;
import scw.integration.bytedance.poi.DataExternalPoiBaseRequest;
import scw.integration.bytedance.poi.DataExternalPoiBaseResult;
import scw.integration.bytedance.poi.DataExternalPoiBillboardRequest;
import scw.integration.bytedance.poi.DataExternalPoiBillboardResult;
import scw.integration.bytedance.poi.DataExternalPoiClaim;
import scw.integration.bytedance.poi.DataExternalPoiClaimListRequest;
import scw.integration.bytedance.poi.DataExternalPoiServiceBaseRequest;
import scw.integration.bytedance.poi.DataExternalPoiServiceBaseResult;
import scw.integration.bytedance.poi.DataExternalPoiServiceUserRequest;
import scw.integration.bytedance.poi.DataExternalPoiUser;
import scw.integration.bytedance.poi.DataExternalPoiUserRequest;
import scw.integration.bytedance.poi.MatchResultList;
import scw.integration.bytedance.poi.PoiBaseQueryAmapRequest;
import scw.integration.bytedance.poi.PoiBaseQueryAmapResponse;
import scw.integration.bytedance.poi.PoiExtHotelOrderCancelRequest;
import scw.integration.bytedance.poi.PoiExtHotelOrderCancelResponse;
import scw.integration.bytedance.poi.PoiExtHotelOrderCommitRequest;
import scw.integration.bytedance.poi.PoiExtHotelOrderCommitResponse;
import scw.integration.bytedance.poi.PoiExtHotelOrderStatusRequest;
import scw.integration.bytedance.poi.PoiExtHotelOrderStatusResponse;
import scw.integration.bytedance.poi.PoiExtHotelSku;
import scw.integration.bytedance.poi.PoiExtHotelSkuRequest;
import scw.integration.bytedance.poi.PoiExtPresaleGrouponOrder;
import scw.integration.bytedance.poi.PoiExtPresaleGrouponOrderCommitRequest;
import scw.integration.bytedance.poi.PoiExtPresaleGrouponOrderCreateRequest;
import scw.integration.bytedance.poi.PoiExtPresaleGrouponOrderCreateResponse;
import scw.integration.bytedance.poi.PoiOrderBillToken;
import scw.integration.bytedance.poi.PoiOrderBillTokenRequest;
import scw.integration.bytedance.poi.PoiOrderConfirmRequest;
import scw.integration.bytedance.poi.PoiOrderConfirmResponse;
import scw.integration.bytedance.poi.PoiOrderListToken;
import scw.integration.bytedance.poi.PoiOrderListTokenRequest;
import scw.integration.bytedance.poi.PoiOrderStatusRequest;
import scw.integration.bytedance.poi.PoiOrderSyncRequest;
import scw.integration.bytedance.poi.PoiOrderSyncResponse;
import scw.integration.bytedance.poi.PoiQueryRequest;
import scw.integration.bytedance.poi.PoiQueryResponse;
import scw.integration.bytedance.poi.PoiSkuSyncRequest;
import scw.integration.bytedance.poi.PoiSpuQueryRequest;
import scw.integration.bytedance.poi.PoiSpuQueryResponse;
import scw.integration.bytedance.poi.PoiSupplier;
import scw.integration.bytedance.poi.PoiSupplierQueryRequest;
import scw.integration.bytedance.poi.PoiSupplierQueryResponse;
import scw.integration.bytedance.poi.PoiSupplierSyncResponse;
import scw.integration.bytedance.poi.PoiV2SpuGetRequest;
import scw.integration.bytedance.poi.PoiV2SpuGetResponse;
import scw.integration.bytedance.poi.PoiV2SpuStatusRequest;
import scw.integration.bytedance.poi.PoiV2SpuStatusResponse;
import scw.integration.bytedance.poi.PoiV2SpuStatusSyncRequest;
import scw.integration.bytedance.poi.PoiV2SpuStatusSyncResponse;
import scw.integration.bytedance.poi.PoiV2SpuStockUpdateRequest;
import scw.integration.bytedance.poi.PoiV2SpuStockUpdateResponse;
import scw.integration.bytedance.poi.PoiV2SpuSyncRequest;
import scw.integration.bytedance.poi.PoiV2SpuSyncResponse;
import scw.integration.bytedance.poi.PoiV2SpuTakeRateSyncRequest;
import scw.integration.bytedance.poi.PoiV2SpuTakeRateSyncResponse;
import scw.integration.bytedance.poi.PoiV2SupplierMatchRequest;
import scw.integration.bytedance.poi.PoiV2SupplierMatchResponse;
import scw.integration.bytedance.poi.PoiV2SupplierQuerySupplier;
import scw.integration.bytedance.poi.PoiV2SupplierQueryTask;
import scw.integration.bytedance.poi.PoiV2SupplierQueryTaskRequest;
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
		ValidationUtils.validate(() -> FastValidator.getValidator().validate(
				request));
		return MapperUtils.getMapper().getFields(OauthAccessTokenRequest.class)
				.accept(FieldFeature.IGNORE_STATIC).getValueMap(request);
	}

	private TypeDescriptor wrapperResponseType(ResolvableType resposeType) {
		ResolvableType resolvableType = ResolvableType.forClassWithGenerics(
				Response.class, resposeType);
		return TypeDescriptor.valueOf(resolvableType);
	}

	private <P> P post(GateWay gateWay, String path, Object urlRequest,
			Object bodyRequest, MediaType mediaType, ResolvableType responseType) {
		String pathToUse = path;
		if (urlRequest != null) {
			Map<String, Object> parameterMap = validateAndGetParameterMap(urlRequest);
			pathToUse = UriUtils.appendQueryParams(pathToUse, parameterMap,
					URLCodec.UTF_8);
		}

		if (bodyRequest != null) {
			ValidationUtils.validate(() -> FastValidator.getValidator()
					.validate(bodyRequest));
		}

		HttpResponseEntity<P> responseEntity = HttpUtils.getHttpClient().post(
				TypeDescriptor.valueOf(responseType),
				gateWay.getUrl() + pathToUse, bodyRequest, mediaType);
		return responseEntity.getBody();
	}

	private <P> P post(GateWay gateWay, String path, Object urlRequest,
			Object bodyRequest, MediaType mediaType, Class<P> responseType) {
		return post(gateWay, path, urlRequest, bodyRequest, mediaType,
				ResolvableType.forClass(responseType));
	}

	private <P> Response<P> doPost(GateWay gateWay, String path,
			Object urlRequest, Object bodyRequest, MediaType mediaType,
			ResolvableType responseType) {
		return post(gateWay, path, urlRequest, bodyRequest, mediaType,
				ResolvableType.forClassWithGenerics(Response.class,
						responseType));
	}

	private <P> Response<P> doPost(GateWay gateWay, String path,
			Object urlRequest, Object bodyRequest, MediaType mediaType,
			Class<? extends P> responseType) {
		return doPost(gateWay, path, urlRequest, bodyRequest, mediaType,
				ResolvableType.forClass(responseType));
	}

	private <P> Response<P> doGet(GateWay gateWay, String path, Object request,
			ResolvableType responseType) {
		Map<String, Object> parameterMap = validateAndGetParameterMap(request);
		String url = UriUtils.appendQueryParams(gateWay.getUrl() + path,
				parameterMap, URLCodec.UTF_8);
		HttpResponseEntity<Response<P>> responseEntity = HttpUtils
				.getHttpClient().get(wrapperResponseType(responseType), url);
		return responseEntity.getBody();
	}

	private <P> Response<P> doGet(GateWay gateWay, String path, Object request,
			Class<? extends P> responseType) {
		return doGet(gateWay, path, request,
				ResolvableType.forClass(responseType));
	}

	public Response<OauthAccessTokenResponse> oauthAccessToken(GateWay gateWay,
			OauthAccessTokenRequest request) {
		return doGet(gateWay, "/oauth/access_token/", request,
				OauthAccessTokenResponse.class);
	}

	public Response<OauthRenewRefreshTokenResponse> oauthRenewRefreshToken(
			GateWay gateWay, OauthRenewRefreshTokenRequest request) {
		return doPost(gateWay, "/oauth/renew_refresh_token/", null, request,
				MediaType.MULTIPART_FORM_DATA,
				OauthRenewRefreshTokenResponse.class);
	}

	public Response<OauthClientTokenResponse> oauthClientToken(GateWay gateWay,
			OauthClientTokenRequest request) {
		return doGet(gateWay, "/oauth/client_token/", request,
				OauthClientTokenResponse.class);
	}

	public Response<OauthAccessTokenResponse> oauthRefreshToken(
			GateWay gateWay, OauthRefreshTokenRequest request) {
		return doGet(gateWay, "/oauth/refresh_token/", request,
				OauthAccessTokenResponse.class);
	}

	public Response<UserinfoResponse> oauthUserinfo(GateWay gateWay,
			UserRequest request) {
		return doGet(gateWay, "/oauth/userinfo/", request,
				UserinfoResponse.class);
	}

	public Response<FansListResponse> fansList(GateWay gateWay,
			UserPagingRequest request) {
		return doGet(gateWay, "/fans/list/", request, FansListResponse.class);
	}

	public Response<FollowingListResponse> followingList(GateWay gateWay,
			UserPagingRequest request) {
		return doGet(gateWay, "/following/list/", request,
				FollowingListResponse.class);
	}

	public Response<FansCheckResponse> fansCheck(GateWay gateWay,
			FansCheckRequest request) {
		return doGet(gateWay, "/fans/check/", request, FansCheckResponse.class);
	}

	public String decryptMobile(String clientSecret, String encryptedMobile) {
		byte[] bytes = clientSecret.getBytes();
		return CharsetCodec.DEFAULT.to(new AES(bytes, bytes))
				.to(Base64.DEFAULT).decode(encryptedMobile);
	}

	public Response<VideoUploadResponse> videoUpload(GateWay gateWay,
			UserRequest request, Resource video) {
		return doPost(gateWay,
				gateWay.getVideoContextPath() + "/video/upload/", request,
				Collections.singletonMap("video", video),
				MediaType.MULTIPART_FORM_DATA, VideoUploadResponse.class);
	}

	public Response<VideoPartInitResponse> videoPartInit(GateWay gateWay,
			UserRequest request) {
		return doPost(gateWay, gateWay.getVideoContextPath()
				+ "/video/part/init/", request, null,
				MediaType.APPLICATION_JSON, VideoPartInitResponse.class);
	}

	public Response<ResponseSubCode> videoPartUpload(GateWay gateWay,
			VdeoPartUploadRequest request, Resource resource) {
		return doPost(gateWay, gateWay.getVideoContextPath()
				+ "/video/part/upload/", request,
				Collections.singletonMap("video", resource),
				MediaType.MULTIPART_FORM_DATA, ResponseSubCode.class);
	}

	public Response<VideoUploadResponse> videoPartComplete(GateWay gateWay,
			VideoPartRequest request) {
		return doPost(gateWay, gateWay.getVideoContextPath()
				+ "/video/part/complete/", request, null,
				MediaType.APPLICATION_JSON, VideoUploadResponse.class);
	}

	public Response<CreateResponse> videoCreate(UserRequest user,
			VideoCreateRequest request) {
		return doPost(GateWay.DOUYIN, "/video/create/", user, request,
				MediaType.APPLICATION_JSON, CreateResponse.class);
	}

	public Response<ImageUploadResponse> imageUpload(UserRequest request,
			Resource image) {
		return doPost(GateWay.DOUYIN, "/image/upload/", request,
				Collections.singletonMap("image", image),
				MediaType.MULTIPART_FORM_DATA, ImageUploadResponse.class);
	}

	public Response<CreateResponse> imageCreate(UserRequest user,
			ImageCreateRequest request) {
		return doPost(GateWay.DOUYIN, "/image/create/", user, request,
				MediaType.APPLICATION_JSON, CreateResponse.class);
	}

	public Response<ResponseCode> deleteVideo(UserRequest user, String itemId) {
		return doPost(GateWay.DOUYIN, "/video/delete/", user,
				Collections.singletonMap("item_id", itemId),
				MediaType.APPLICATION_JSON, ResponseCode.class);
	}

	public Response<VideoListResponse> videoList(GateWay gateWay,
			UserPagingRequest request) {
		return doGet(gateWay, gateWay.getVideoContextPath() + "/video/list/",
				request, VideoListResponse.class);
	}

	public Response<VideoDataResponse> videoData(GateWay gateWay,
			UserRequest user, Collection<String> item_ids) {
		return doPost(gateWay, gateWay.getVideoContextPath() + "/video/data/",
				user, Collections.singletonMap("item_ids", item_ids),
				MediaType.APPLICATION_JSON, VideoDataResponse.class);
	}

	public Response<ShareIdResponse> shareId(ShareIdRequest request) {
		return doGet(GateWay.DOUYIN, "/share-id/", request,
				ShareIdResponse.class);
	}

	public Response<PoiSearchKeywordResponse> poiSearchKeyword(
			PoiSearchKeywordRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/search/keyword/", request,
				PoiSearchKeywordResponse.class);
	}

	public Response<CommentListResponse> commentList(CommentApiType apiType,
			CommentListRequest request) {
		return doGet(GateWay.DOUYIN, apiType.getContextPath()
				+ "/comment/list/", request, CommentListResponse.class);
	}

	public Response<CommentListResponse> commentReplyList(
			CommentApiType apiType, CommentReplyListRequest reqeust) {
		return doGet(GateWay.DOUYIN, apiType.getContextPath()
				+ "/comment/reply/list/", reqeust, CommentListResponse.class);
	}

	public Response<CommentReplyResponse> commentReply(CommentApiType apiType,
			UserRequest user, CommentReplyRequest request) {
		return doPost(GateWay.DOUYIN, apiType.getContextPath()
				+ "/comment/reply/", user, request, MediaType.APPLICATION_JSON,
				CommentReplyResponse.class);
	}

	public Response<EnterpriseImMessageSendResponse> enterpriseImMessageSend(
			UserRequest user, EnterpriseImMessageSendRequest request) {
		return doPost(GateWay.DOUYIN, "/enterprise/im/message/send/", user,
				request, MediaType.APPLICATION_JSON,
				EnterpriseImMessageSendResponse.class);
	}

	public Response<VideoSearchResponse> videoSearch(VideoSearchRequest request) {
		return doGet(GateWay.DOUYIN, "/video/search/", request,
				VideoSearchResponse.class);
	}

	public Response<CommentListResponse> videoSearchCommentList(
			VideoSearchCommentListRequest request) {
		return doGet(GateWay.DOUYIN, "/video/search/comment/list/", request,
				CommentListResponse.class);
	}

	public Response<CommentReplyResponse> videoSearchCommentReply(
			UserRequest user, VideoSearchCommentReplyRequest request) {
		return doPost(GateWay.DOUYIN, "/video/search/comment/reply/", user,
				request, MediaType.APPLICATION_JSON, CommentReplyResponse.class);
	}

	public Response<CommentListResponse> videoSearchCommentReplyList(
			VideoSearchCommentReplyListRequest request) {
		return doGet(GateWay.DOUYIN, "/video/search/comment/reply/list/",
				request, CommentListResponse.class);
	}

	public Response<DataExternalUserResponse<DataExternalUserItem>> dataExternalUserItem(
			DataExternalUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/user/item/", request,
				ResolvableType.forClassWithGenerics(
						DataExternalUserResponse.class,
						DataExternalUserItem.class));
	}

	public Response<DataExternalUserResponse<DataExternalUserFans>> dataExternalUserFans(
			DataExternalUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/user/fans/", request,
				ResolvableType.forClassWithGenerics(
						DataExternalUserResponse.class,
						DataExternalUserItem.class));
	}

	public Response<DataExternalUserResponse<DataExternalUserLike>> dataExternalUserLike(
			DataExternalUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/user/like/", request,
				ResolvableType.forClassWithGenerics(
						DataExternalUserResponse.class,
						DataExternalUserLike.class));
	}

	public Response<DataExternalUserResponse<DataExternalUserComment>> dataExternalUserComment(
			DataExternalUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/user/comment/", request,
				ResolvableType.forClassWithGenerics(
						DataExternalUserResponse.class,
						DataExternalUserComment.class));
	}

	public Response<DataExternalUserResponse<DataExternalUserShare>> dataExternalUserShare(
			DataExternalUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/user/share/", request,
				ResolvableType.forClassWithGenerics(
						DataExternalUserResponse.class,
						DataExternalUserShare.class));
	}

	public Response<DataExternalUserResponse<DataExternalUserProfile>> dataExternalUserProfile(
			DataExternalUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/user/profile/", request,
				ResolvableType.forClassWithGenerics(
						DataExternalUserResponse.class,
						DataExternalUserProfile.class));
	}

	public Response<DataExternalItemBaseResponse> dataExternalItemBase(
			DataExternalItemBaseRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/item/base/", request,
				DataExternalItemBaseResponse.class);
	}

	public Response<DataExternalUserResponse<DataExternalItemLike>> dataExternalItemLike(
			DataExternalItemRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/item/like/", request,
				ResolvableType.forClassWithGenerics(
						DataExternalUserResponse.class,
						DataExternalItemLike.class));
	}

	public Response<DataExternalUserResponse<DataExternalItemComment>> dataExternalItemComment(
			DataExternalItemRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/item/comment/", request,
				ResolvableType.forClassWithGenerics(
						DataExternalUserResponse.class,
						DataExternalItemComment.class));
	}

	public Response<DataExternalUserResponse<DataExternalItemPlay>> dataExternalItemPlay(
			DataExternalItemRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/item/play/", request,
				ResolvableType.forClassWithGenerics(
						DataExternalUserResponse.class,
						DataExternalItemPlay.class));
	}

	public Response<DataExternalUserResponse<DataExternalItemShare>> dataExternalItemShare(
			DataExternalItemRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/item/share/", request,
				ResolvableType.forClassWithGenerics(
						DataExternalUserResponse.class,
						DataExternalItemShare.class));
	}

	public Response<FansDataResponse> fansData(UserRequest request) {
		return doGet(GateWay.DOUYIN, "/fans/data/", request,
				FansDataResponse.class);
	}

	public Response<ListData<DataExternFansSource>> dataExternFansSource(
			UserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/fans/source/", request,
				ResolvableType.forClassWithGenerics(ListData.class,
						DataExternFansSource.class));
	}

	public Response<ListData<DataExternFansFavourite>> dataExternFansFavourite(
			UserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/fans/favourite/", request,
				ResolvableType.forClassWithGenerics(ListData.class,
						DataExternFansFavourite.class));
	}

	public Response<ListData<DataExternFansComment>> dataExternFansComment(
			UserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/fans/comment/", request,
				ResolvableType.forClassWithGenerics(ListData.class,
						DataExternFansComment.class));
	}

	public Response<HotsearchSentencesResponse> hotsearchSentences(
			ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/hotsearch/sentences/", request,
				HotsearchSentencesResponse.class);
	}

	public Response<PagingResponseCodeTotalList<HotsearchTrendingSentences>> hotsearchTrendingSentences(
			ClientPagingRequest request) {
		return doGet(GateWay.DOUYIN, "/hotsearch/trending/sentences/", request,
				ResolvableType.forClassWithGenerics(
						PagingResponseCodeTotalList.class,
						HotsearchTrendingSentences.class));
	}

	public Response<ResponseCodeList<VideoData>> hotsearchVideos(
			HotsearchVideosRequest request) {
		return doGet(GateWay.DOUYIN, "/hotsearch/videos/", request,
				ResolvableType.forClassWithGenerics(ResponseCodeList.class,
						VideoData.class));
	}

	public Response<StarHotListResponse> starHotList(StarHotListRequest request) {
		return doGet(GateWay.DOUYIN, "/star/hot_list/", request,
				StarHotListResponse.class);
	}

	public Response<StarAuthorScore> starAuthorScore(UserRequest request) {
		return doGet(GateWay.DOUYIN, "/star/author_score/", request,
				StarAuthorScore.class);
	}

	public Response<StarAuthorScoreV2Response> starAuthorScoreV2(
			StarAuthorScoreV2Request request) {
		return doGet(GateWay.DOUYIN, "/star/author_score_v2/", request,
				StarAuthorScoreV2Response.class);
	}

	public Response<DiscoveryEntRankItemResponse> discoveryEntRankItem(
			DiscoveryEntRankItemRequest request) {
		return doGet(GateWay.DOUYIN, "/discovery/ent/rank/item/", request,
				DiscoveryEntRankItemResponse.class);
	}

	public Response<PagingResponseCodeList<DiscoveryEntRrankVersion>> discoveryEntRrankVersion(
			DiscoveryEntRrankVersionRequest request) {
		return doGet(GateWay.DOUYIN, "/discovery/ent/rank/version/", request,
				ResolvableType.forClassWithGenerics(
						PagingResponseCodeList.class,
						DiscoveryEntRrankVersion.class));
	}

	public Response<ResponseCodeResultList<DataExternalSdkShare>> dataExternalSdkShare(
			DataExternalSdkShare request) {
		return doGet(GateWay.DOUYIN, "/data/external/sdk_share/", request,
				ResolvableType.forClassWithGenerics(
						ResponseCodeResultList.class,
						DataExternalSdkShare.class));
	}

	public Response<ResponseCodeResultList<DataExternalAnchorMpItemClickDistribution>> dataExternalAnchorMpItemClickDistribution(
			DataExternalAnchorMpItemClickDistributionRequest request) {
		return doGet(GateWay.DOUYIN,
				"/data/external/anchor/mp_item_click_distribution/", request,
				ResolvableType.forClassWithGenerics(
						ResponseCodeResultList.class,
						DataExternalAnchorMpItemClickDistribution.class));
	}

	public Response<ListData<DataExternBillboardHotVideo>> dataExternBillboardHotVideo(
			ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/billboard/hot_video/",
				request, ResolvableType.forClassWithGenerics(ListData.class,
						DataExternBillboardHotVideo.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardSport(
			SportRankApiType apiType, ClientRequest request) {
		return doGet(GateWay.DOUYIN, apiType.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class,
						DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardAmusement(
			AmusementRankApiType apiType, ClientRequest request) {
		return doGet(GateWay.DOUYIN, apiType.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class,
						DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardGame(
			GameRankApiType apiType, ClientRequest request) {
		return doGet(GateWay.DOUYIN, apiType.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class,
						DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardFood(
			FoodRankApiType apiType, ClientRequest request) {
		return doGet(GateWay.DOUYIN, apiType.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class,
						DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardDramaOverall(
			ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/billboard/drama/overall/",
				request, ResolvableType.forClassWithGenerics(ListData.class,
						DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardCar(
			CarRankApiType apiType, ClientRequest request) {
		return doGet(GateWay.DOUYIN, apiType.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class,
						DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardTravelOverall(
			TravelRankApiType apiType, ClientRequest request) {
		return doGet(GateWay.DOUYIN, apiType.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class,
						DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardCospa(
			CospaRankApiType type, ClientRequest request) {
		return doGet(GateWay.DOUYIN, type.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class,
						DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboardStars>> dataExternBillboardStars(
			ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/billboard/stars/", request,
				ResolvableType.forClassWithGenerics(ListData.class,
						DataExternBillboardStars.class));
	}

	public Response<ListData<DataExternBillboardLive>> dataExternBillboardLive(
			ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/billboard/live/", request,
				ResolvableType.forClassWithGenerics(ListData.class,
						DataExternBillboardLive.class));
	}

	public Response<ListData<DataExternBillboardMusic>> dataExternBillboardMusic(
			MusicRankApiType type, ClientRequest request) {
		return doGet(GateWay.DOUYIN, type.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class,
						DataExternBillboardMusic.class));
	}

	public Response<ListData<DataExternBillboardTopic>> dataExternBillboardTopic(
			ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/billboard/topic/", request,
				ResolvableType.forClassWithGenerics(ListData.class,
						DataExternBillboardTopic.class));
	}

	public Response<ListData<DataExternBillboardProp>> dataExternBillboardProp(
			ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/billboard/prop/", request,
				ResolvableType.forClassWithGenerics(ListData.class,
						DataExternBillboardProp.class));
	}

	public Response<EnterpriseLeadsUserListResponse> enterpriseLeadsUserList(
			EnterpriseLeadsUserListRequest request) {
		return doGet(GateWay.DOUYIN, "/enterprise/leads/user/list/", request,
				EnterpriseLeadsUserListResponse.class);
	}

	public Response<EnterpriseLeadsUserDetailResponse> enterpriseLeadsUserDetail(
			EnterpriseLeadsUserDetailRequest request) {
		return doGet(GateWay.DOUYIN, "/enterprise/leads/user/detail/", request,
				EnterpriseLeadsUserDetailResponse.class);
	}

	public Response<PagingResponseCodeList<EnterpriseLeadsUserAction>> enterpriseLeadsUserActionList(
			EnterpriseLeadsUserActionListRequest request) {
		return doGet(GateWay.DOUYIN, "/enterprise/leads/user/action/list/",
				request, ResolvableType.forClassWithGenerics(
						PagingResponseCodeList.class,
						EnterpriseLeadsUserAction.class));
	}

	public Response<PagingResponseCodeList<Tag>> enterpriseLeadsTagList(
			UserPagingRequest request) {
		return doGet(GateWay.DOUYIN, "/enterprise/leads/tag/list/", request,
				ResolvableType.forClassWithGenerics(
						PagingResponseCodeList.class, Tag.class));
	}

	public Response<PagingResponseCodeList<String>> enterpriseLeadsTagUserList(
			EnterpriseLeadsTagUserListRequest request) {
		return doGet(GateWay.DOUYIN, "/enterprise/leads/tag/user/list/",
				request, ResolvableType.forClassWithGenerics(
						PagingResponseCodeList.class, String.class));
	}

	public Response<EnterpriseLeadsTagResponse> enterpriseLeadsTagCreate(
			UserRequest user, String tag_name) {
		return doPost(GateWay.DOUYIN, "/enterprise/leads/tag/create/", user,
				Collections.singletonMap("tag_name", tag_name),
				MediaType.APPLICATION_JSON, EnterpriseLeadsTagResponse.class);
	}

	public Response<EnterpriseLeadsTagResponse> enterpriseLeadsTagUpdate(
			UserRequest user, Tag tag) {
		return doPost(GateWay.DOUYIN, "/enterprise/leads/tag/update/", user,
				tag, MediaType.APPLICATION_JSON,
				EnterpriseLeadsTagResponse.class);
	}

	public Response<ResponseCode> enterpriseLeadsTagDelete(UserRequest user,
			String tag_id) {
		return doPost(GateWay.DOUYIN, "/enterprise/leads/tag/delete/", user,
				Collections.singletonMap("tag_id", tag_id),
				MediaType.APPLICATION_JSON, ResponseCode.class);
	}

	public Response<ResponseCode> enterpriseLeadsTagUserUpdate(
			UserRequest user, EnterpriseLeadsTagUserUpdateRequest request) {
		return doPost(GateWay.DOUYIN, "/enterprise/leads/tag/user/update/",
				user, request, MediaType.APPLICATION_JSON, ResponseCode.class);
	}

	public Response<EnterpriseImCardSaveResponse> enterpriseImCardSave(
			UserRequest user, EnterpriseImCardSaveRequest request) {
		return doPost(GateWay.DOUYIN, "/enterprise/im/card/save/", user,
				request, MediaType.APPLICATION_JSON,
				EnterpriseImCardSaveResponse.class);
	}

	public Response<EnterpriseImCardListResponse> enterpriseImCardList(
			UserPagingRequest request) {
		return doGet(GateWay.DOUYIN, "/enterprise/im/card/list/", request,
				EnterpriseImCardListResponse.class);
	}

	public Response<ResponseCode> enterpriseImCardDelete(UserRequest user,
			EnterpriseImCardDeleteRequest request) {
		return doPost(GateWay.DOUYIN, "/enterprise/im/card/delete/", user,
				request, MediaType.APPLICATION_JSON, ResponseCode.class);
	}

	public Response<PoiSupplierSyncResponse> poiSupplierSync(
			ClientRequest client, PoiSupplier request) {
		return doPost(GateWay.DOUYIN, "/poi/supplier/sync/", client, request,
				MediaType.APPLICATION_JSON, PoiSupplierSyncResponse.class);
	}

	public Response<PoiSupplierQueryResponse> poiSupplierQuery(
			PoiSupplierQueryRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/supplier/query/", request,
				PoiSupplierQueryResponse.class);
	}

	public Response<PoiQueryResponse> poiQuery(PoiQueryRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/query/", request,
				PoiQueryResponse.class);
	}

	public Response<MatchResultList<PoiV2SupplierQueryTask>> poiV2SupplierQueryTask(
			PoiV2SupplierQueryTaskRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/v2/supplier/query/task/", request,
				ResolvableType.forClassWithGenerics(MatchResultList.class,
						PoiV2SupplierQueryTask.class));
	}

	public Response<MatchResultList<PoiV2SupplierQuerySupplier>> poiV2SupplierQuerySupplier(
			PoiSupplierQueryRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/v2/supplier/query/supplier/",
				request,
				ResolvableType.forClassWithGenerics(MatchResultList.class,
						PoiV2SupplierQuerySupplier.class));
	}

	public Response<PoiV2SupplierMatchResponse> poiV2SupplierMatch(
			ClientRequest client, PoiV2SupplierMatchRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/v2/supplier/match/", client,
				request, MediaType.APPLICATION_JSON,
				PoiV2SupplierMatchResponse.class);
	}

	public Response<PoiSpuQueryResponse> poiSpuQuery(PoiSpuQueryRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/spu/query/", request,
				PoiSpuQueryResponse.class);
	}

	public Response<ResponseCode> poiSkuSync(ClientRequest client,
			PoiSkuSyncRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/sku/sync/", client, request,
				MediaType.APPLICATION_JSON, ResponseCode.class);
	}

	public Response<PoiExtHotelSku> poiExtHotelSku(PoiExtHotelSkuRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/ext/hotel/sku/", request,
				PoiExtHotelSku.class);
	}

	public Response<PoiV2SpuSyncResponse> poiV2SpuSync(ClientRequest client,
			PoiV2SpuSyncRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/v2/spu/sync/", client, request,
				MediaType.APPLICATION_JSON, PoiV2SpuSyncResponse.class);
	}

	public Response<PoiV2SpuStatusSyncResponse> poiV2SpuStatusSync(
			ClientRequest client, PoiV2SpuStatusSyncRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/v2/spu/status_sync/", client,
				request, MediaType.APPLICATION_JSON,
				PoiV2SpuStatusSyncResponse.class);
	}

	public Response<PoiV2SpuStockUpdateResponse> poiV2SpuStockUpdate(
			ClientRequest client, PoiV2SpuStockUpdateRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/v2/spu/stock_update/", client,
				request, MediaType.APPLICATION_JSON,
				PoiV2SpuStockUpdateResponse.class);
	}

	public Response<PoiV2SpuStatusResponse> poiV2SpuStatus(
			PoiV2SpuStatusRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/v2/spu/status/", request,
				PoiV2SpuStatusResponse.class);
	}

	public Response<PoiV2SpuGetResponse> poiV2SpuGet(PoiV2SpuGetRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/v2/spu/get/", request,
				PoiV2SpuGetResponse.class);
	}

	public Response<PoiV2SpuTakeRateSyncResponse> poiV2SpuTakeRateSync(
			ClientRequest client, PoiV2SpuTakeRateSyncRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/v2/spu/take_rate/sync/", client,
				request, MediaType.APPLICATION_JSON,
				PoiV2SpuTakeRateSyncResponse.class);
	}

	public Response<ResponseCode> poiOrderStatus(ClientRequest client,
			PoiOrderStatusRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/order/status/", client, request,
				MediaType.APPLICATION_JSON, ResponseCode.class);
	}

	public PoiExtHotelOrderCommitResponse poiExtHotelOrderCommit(
			ClientRequest client, PoiExtHotelOrderCommitRequest request) {
		return post(GateWay.DOUYIN, "/poi/ext/hotel/order/commit/", client,
				request, MediaType.APPLICATION_JSON,
				PoiExtHotelOrderCommitResponse.class);
	}

	public PoiExtHotelOrderStatusResponse poiExtHotelOrderStatus(
			PoiExtHotelOrderStatusRequest request) {
		return post(GateWay.DOUYIN, "/poi/ext/hotel/order/status/", null,
				request, MediaType.APPLICATION_JSON,
				PoiExtHotelOrderStatusResponse.class);
	}

	public PoiExtHotelOrderCancelResponse poiExtHotelOrderCancel(
			PoiExtHotelOrderCancelRequest request) {
		return post(GateWay.DOUYIN, "/poi/ext/hotel/order/cancel/", null,
				request, MediaType.APPLICATION_JSON,
				PoiExtHotelOrderCancelResponse.class);
	}

	public PoiOrderConfirmResponse poiOrderConfirm(ClientRequest client,
			PoiOrderConfirmRequest request) {
		return post(GateWay.DOUYIN, "/poi/order/confirm/", client, request,
				MediaType.APPLICATION_JSON, PoiOrderConfirmResponse.class);
	}

	public Response<PoiExtPresaleGrouponOrderCreateResponse> poiExtPresaleGrouponOrderCreate(
			PoiExtPresaleGrouponOrderCreateRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/ext/presale_groupon/order/create/",
				null, request, MediaType.APPLICATION_JSON,
				PoiExtPresaleGrouponOrderCreateResponse.class);
	}

	public Response<PoiExtPresaleGrouponOrder> poiExtPresaleGrouponOrderCommit(
			PoiExtPresaleGrouponOrderCommitRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/ext/presale_groupon/order/commit/",
				null, request, MediaType.APPLICATION_JSON,
				PoiExtPresaleGrouponOrder.class);
	}

	public Response<PoiExtPresaleGrouponOrder> poiExtPresaleGrouponOrderCancel(
			PoiExtPresaleGrouponOrder request) {
		return doPost(GateWay.DOUYIN, "/poi/ext/presale_groupon/order/cancel/",
				null, request, MediaType.APPLICATION_JSON,
				PoiExtPresaleGrouponOrder.class);
	}

	public Response<PoiOrderBillToken> poiOrderBillToken(
			PoiOrderBillTokenRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/order/bill/token/", request,
				PoiOrderBillToken.class);
	}

	public Response<PoiOrderListToken> poiOrderListToken(
			PoiOrderListTokenRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/order/list/token/", request,
				PoiOrderListToken.class);
	}

	public Response<PoiOrderSyncResponse> poiOrderSync(ClientRequest client,
			PoiOrderSyncRequest request) {
		return doPost(GateWay.DOUYIN, " /poi/order/sync/", client, request,
				MediaType.APPLICATION_JSON, PoiOrderSyncResponse.class);
	}

	public Response<ResponseCodeResultList<DataExternalPoiBaseResult>> dataExternalPoiBase(
			DataExternalPoiBaseRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/poi/base/", request,
				ResolvableType.forClassWithGenerics(
						ResponseCodeResultList.class,
						DataExternalPoiBaseResult.class));
	}

	public Response<DataExternalPoiUser> dataExternalPoiUser(
			DataExternalPoiUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/poi/user/", request,
				DataExternalPoiUser.class);
	}

	public Response<ResponseCodeResultList<DataExternalPoiServiceBaseResult>> dataExternalPoiServiceBase(
			DataExternalPoiServiceBaseRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/poi/service_base/",
				request, ResolvableType.forClassWithGenerics(
						ResponseCodeResultList.class,
						DataExternalPoiServiceBaseResult.class));
	}

	public Response<DataExternalPoiUser> dataExternalPoiServiceUser(
			DataExternalPoiServiceUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/poi/service_user/",
				request, DataExternalPoiUser.class);
	}

	public Response<ResponseCodeResultList<DataExternalPoiBillboardResult>> dataExternalPoiBillboard(
			DataExternalPoiBillboardRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/poi/billboard/", request,
				ResolvableType.forClassWithGenerics(
						ResponseCodeResultList.class,
						DataExternalPoiBillboardResult.class));
	}

	public Response<PagingResponseCodeTotalList<DataExternalPoiClaim>> dataExternalPoiClaimList(
			DataExternalPoiClaimListRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/poi/claim/list/", request,
				ResolvableType.forClassWithGenerics(
						PagingResponseCodeTotalList.class,
						DataExternalPoiClaim.class));
	}

	public Response<PoiBaseQueryAmapResponse> poiBaseQueryAmap(
			PoiBaseQueryAmapRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/base/query/amap/", request,
				PoiBaseQueryAmapResponse.class);
	}

	public Response<DevtoolMicappIsLegalResponse> devtoolMicappIsLegal(
			DevtoolMicappIsLegalRequest request) {
		return doGet(GateWay.DOUYIN, "/devtool/micapp/is_legal/", request,
				DevtoolMicappIsLegalResponse.class);
	}

	public Response<ResponseCode> sandboxWebhookEventSend(ClientRequest client,
			SandboxWebhookEventSendRequest request) {
		return doPost(GateWay.DOUYIN, "/sandbox/webhook/event/send/", client,
				request, MediaType.APPLICATION_JSON, ResponseCode.class);
	}

	public Response<JsGetticketResponse> jsGetticket(ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/js/getticket/", request,
				JsGetticketResponse.class);
	}
}
