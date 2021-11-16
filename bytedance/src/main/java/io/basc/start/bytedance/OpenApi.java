package io.basc.start.bytedance;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import io.basc.framework.codec.support.AES;
import io.basc.framework.codec.support.Base64;
import io.basc.framework.codec.support.CharsetCodec;
import io.basc.framework.codec.support.URLCodec;
import io.basc.framework.convert.TypeDescriptor;
import io.basc.framework.core.ResolvableType;
import io.basc.framework.http.HttpResponseEntity;
import io.basc.framework.http.HttpUtils;
import io.basc.framework.http.MediaType;
import io.basc.framework.io.Resource;
import io.basc.framework.mapper.MapperUtils;
import io.basc.framework.net.uri.UriUtils;
import io.basc.framework.validation.FastValidator;
import io.basc.start.bytedance.comment.CommentApiType;
import io.basc.start.bytedance.comment.CommentListRequest;
import io.basc.start.bytedance.comment.CommentListResponse;
import io.basc.start.bytedance.comment.CommentReplyListRequest;
import io.basc.start.bytedance.comment.CommentReplyRequest;
import io.basc.start.bytedance.comment.CommentReplyResponse;
import io.basc.start.bytedance.comment.EnterpriseImMessageSendRequest;
import io.basc.start.bytedance.comment.EnterpriseImMessageSendResponse;
import io.basc.start.bytedance.data.AmusementRankApiType;
import io.basc.start.bytedance.data.CarRankApiType;
import io.basc.start.bytedance.data.CospaRankApiType;
import io.basc.start.bytedance.data.DataExternBillboard;
import io.basc.start.bytedance.data.DataExternBillboardHotVideo;
import io.basc.start.bytedance.data.DataExternBillboardLive;
import io.basc.start.bytedance.data.DataExternBillboardMusic;
import io.basc.start.bytedance.data.DataExternBillboardProp;
import io.basc.start.bytedance.data.DataExternBillboardStars;
import io.basc.start.bytedance.data.DataExternBillboardTopic;
import io.basc.start.bytedance.data.DataExternFansComment;
import io.basc.start.bytedance.data.DataExternFansFavourite;
import io.basc.start.bytedance.data.DataExternFansSource;
import io.basc.start.bytedance.data.DataExternalAnchorMpItemClickDistribution;
import io.basc.start.bytedance.data.DataExternalAnchorMpItemClickDistributionRequest;
import io.basc.start.bytedance.data.DataExternalItemBaseRequest;
import io.basc.start.bytedance.data.DataExternalItemBaseResponse;
import io.basc.start.bytedance.data.DataExternalItemComment;
import io.basc.start.bytedance.data.DataExternalItemLike;
import io.basc.start.bytedance.data.DataExternalItemPlay;
import io.basc.start.bytedance.data.DataExternalItemRequest;
import io.basc.start.bytedance.data.DataExternalItemShare;
import io.basc.start.bytedance.data.DataExternalSdkShare;
import io.basc.start.bytedance.data.DataExternalUserComment;
import io.basc.start.bytedance.data.DataExternalUserFans;
import io.basc.start.bytedance.data.DataExternalUserItem;
import io.basc.start.bytedance.data.DataExternalUserLike;
import io.basc.start.bytedance.data.DataExternalUserProfile;
import io.basc.start.bytedance.data.DataExternalUserRequest;
import io.basc.start.bytedance.data.DataExternalUserResponse;
import io.basc.start.bytedance.data.DataExternalUserShare;
import io.basc.start.bytedance.data.DiscoveryEntRankItemRequest;
import io.basc.start.bytedance.data.DiscoveryEntRankItemResponse;
import io.basc.start.bytedance.data.DiscoveryEntRrankVersion;
import io.basc.start.bytedance.data.DiscoveryEntRrankVersionRequest;
import io.basc.start.bytedance.data.FansDataResponse;
import io.basc.start.bytedance.data.FoodRankApiType;
import io.basc.start.bytedance.data.GameRankApiType;
import io.basc.start.bytedance.data.HotsearchSentencesResponse;
import io.basc.start.bytedance.data.HotsearchTrendingSentences;
import io.basc.start.bytedance.data.HotsearchVideosRequest;
import io.basc.start.bytedance.data.MusicRankApiType;
import io.basc.start.bytedance.data.SportRankApiType;
import io.basc.start.bytedance.data.StarAuthorScore;
import io.basc.start.bytedance.data.StarAuthorScoreV2Request;
import io.basc.start.bytedance.data.StarAuthorScoreV2Response;
import io.basc.start.bytedance.data.StarHotListRequest;
import io.basc.start.bytedance.data.StarHotListResponse;
import io.basc.start.bytedance.data.TravelRankApiType;
import io.basc.start.bytedance.devtool.DevtoolMicappIsLegalRequest;
import io.basc.start.bytedance.devtool.DevtoolMicappIsLegalResponse;
import io.basc.start.bytedance.devtool.JsGetticketResponse;
import io.basc.start.bytedance.devtool.SandboxWebhookEventSendRequest;
import io.basc.start.bytedance.enterprise.EnterpriseImCardDeleteRequest;
import io.basc.start.bytedance.enterprise.EnterpriseImCardListResponse;
import io.basc.start.bytedance.enterprise.EnterpriseImCardSaveRequest;
import io.basc.start.bytedance.enterprise.EnterpriseImCardSaveResponse;
import io.basc.start.bytedance.enterprise.EnterpriseLeadsTagResponse;
import io.basc.start.bytedance.enterprise.EnterpriseLeadsTagUserListRequest;
import io.basc.start.bytedance.enterprise.EnterpriseLeadsTagUserUpdateRequest;
import io.basc.start.bytedance.enterprise.EnterpriseLeadsUserAction;
import io.basc.start.bytedance.enterprise.EnterpriseLeadsUserActionListRequest;
import io.basc.start.bytedance.enterprise.EnterpriseLeadsUserDetailRequest;
import io.basc.start.bytedance.enterprise.EnterpriseLeadsUserDetailResponse;
import io.basc.start.bytedance.enterprise.EnterpriseLeadsUserListRequest;
import io.basc.start.bytedance.enterprise.EnterpriseLeadsUserListResponse;
import io.basc.start.bytedance.enterprise.Tag;
import io.basc.start.bytedance.oauth.ClientPagingRequest;
import io.basc.start.bytedance.oauth.ClientRequest;
import io.basc.start.bytedance.oauth.OauthAccessTokenRequest;
import io.basc.start.bytedance.oauth.OauthAccessTokenResponse;
import io.basc.start.bytedance.oauth.OauthClientTokenRequest;
import io.basc.start.bytedance.oauth.OauthClientTokenResponse;
import io.basc.start.bytedance.oauth.OauthRefreshTokenRequest;
import io.basc.start.bytedance.oauth.OauthRenewRefreshTokenRequest;
import io.basc.start.bytedance.oauth.OauthRenewRefreshTokenResponse;
import io.basc.start.bytedance.poi.DataExternalPoiBaseRequest;
import io.basc.start.bytedance.poi.DataExternalPoiBaseResult;
import io.basc.start.bytedance.poi.DataExternalPoiBillboardRequest;
import io.basc.start.bytedance.poi.DataExternalPoiBillboardResult;
import io.basc.start.bytedance.poi.DataExternalPoiClaim;
import io.basc.start.bytedance.poi.DataExternalPoiClaimListRequest;
import io.basc.start.bytedance.poi.DataExternalPoiServiceBaseRequest;
import io.basc.start.bytedance.poi.DataExternalPoiServiceBaseResult;
import io.basc.start.bytedance.poi.DataExternalPoiServiceUserRequest;
import io.basc.start.bytedance.poi.DataExternalPoiUser;
import io.basc.start.bytedance.poi.DataExternalPoiUserRequest;
import io.basc.start.bytedance.poi.MatchResultList;
import io.basc.start.bytedance.poi.PoiBaseQueryAmapRequest;
import io.basc.start.bytedance.poi.PoiBaseQueryAmapResponse;
import io.basc.start.bytedance.poi.PoiExtHotelOrderCancelRequest;
import io.basc.start.bytedance.poi.PoiExtHotelOrderCancelResponse;
import io.basc.start.bytedance.poi.PoiExtHotelOrderCommitRequest;
import io.basc.start.bytedance.poi.PoiExtHotelOrderCommitResponse;
import io.basc.start.bytedance.poi.PoiExtHotelOrderStatusRequest;
import io.basc.start.bytedance.poi.PoiExtHotelOrderStatusResponse;
import io.basc.start.bytedance.poi.PoiExtHotelSku;
import io.basc.start.bytedance.poi.PoiExtHotelSkuRequest;
import io.basc.start.bytedance.poi.PoiExtPresaleGrouponOrder;
import io.basc.start.bytedance.poi.PoiExtPresaleGrouponOrderCommitRequest;
import io.basc.start.bytedance.poi.PoiExtPresaleGrouponOrderCreateRequest;
import io.basc.start.bytedance.poi.PoiExtPresaleGrouponOrderCreateResponse;
import io.basc.start.bytedance.poi.PoiOrderBillToken;
import io.basc.start.bytedance.poi.PoiOrderBillTokenRequest;
import io.basc.start.bytedance.poi.PoiOrderConfirmRequest;
import io.basc.start.bytedance.poi.PoiOrderConfirmResponse;
import io.basc.start.bytedance.poi.PoiOrderListToken;
import io.basc.start.bytedance.poi.PoiOrderListTokenRequest;
import io.basc.start.bytedance.poi.PoiOrderStatusRequest;
import io.basc.start.bytedance.poi.PoiOrderSyncRequest;
import io.basc.start.bytedance.poi.PoiOrderSyncResponse;
import io.basc.start.bytedance.poi.PoiQueryRequest;
import io.basc.start.bytedance.poi.PoiQueryResponse;
import io.basc.start.bytedance.poi.PoiSkuSyncRequest;
import io.basc.start.bytedance.poi.PoiSpuQueryRequest;
import io.basc.start.bytedance.poi.PoiSpuQueryResponse;
import io.basc.start.bytedance.poi.PoiSupplier;
import io.basc.start.bytedance.poi.PoiSupplierQueryRequest;
import io.basc.start.bytedance.poi.PoiSupplierQueryResponse;
import io.basc.start.bytedance.poi.PoiSupplierSyncResponse;
import io.basc.start.bytedance.poi.PoiV2SpuGetRequest;
import io.basc.start.bytedance.poi.PoiV2SpuGetResponse;
import io.basc.start.bytedance.poi.PoiV2SpuStatusRequest;
import io.basc.start.bytedance.poi.PoiV2SpuStatusResponse;
import io.basc.start.bytedance.poi.PoiV2SpuStatusSyncRequest;
import io.basc.start.bytedance.poi.PoiV2SpuStatusSyncResponse;
import io.basc.start.bytedance.poi.PoiV2SpuStockUpdateRequest;
import io.basc.start.bytedance.poi.PoiV2SpuStockUpdateResponse;
import io.basc.start.bytedance.poi.PoiV2SpuSyncRequest;
import io.basc.start.bytedance.poi.PoiV2SpuSyncResponse;
import io.basc.start.bytedance.poi.PoiV2SpuTakeRateSyncRequest;
import io.basc.start.bytedance.poi.PoiV2SpuTakeRateSyncResponse;
import io.basc.start.bytedance.poi.PoiV2SupplierMatchRequest;
import io.basc.start.bytedance.poi.PoiV2SupplierMatchResponse;
import io.basc.start.bytedance.poi.PoiV2SupplierQuerySupplier;
import io.basc.start.bytedance.poi.PoiV2SupplierQueryTask;
import io.basc.start.bytedance.poi.PoiV2SupplierQueryTaskRequest;
import io.basc.start.bytedance.search.VideoSearchCommentListRequest;
import io.basc.start.bytedance.search.VideoSearchCommentReplyListRequest;
import io.basc.start.bytedance.search.VideoSearchCommentReplyRequest;
import io.basc.start.bytedance.search.VideoSearchRequest;
import io.basc.start.bytedance.search.VideoSearchResponse;
import io.basc.start.bytedance.user.FansCheckRequest;
import io.basc.start.bytedance.user.FansCheckResponse;
import io.basc.start.bytedance.user.FansListResponse;
import io.basc.start.bytedance.user.FollowingListResponse;
import io.basc.start.bytedance.user.UserPagingRequest;
import io.basc.start.bytedance.user.UserRequest;
import io.basc.start.bytedance.user.UserinfoResponse;
import io.basc.start.bytedance.video.CreateResponse;
import io.basc.start.bytedance.video.ImageCreateRequest;
import io.basc.start.bytedance.video.ImageUploadResponse;
import io.basc.start.bytedance.video.PoiSearchKeywordRequest;
import io.basc.start.bytedance.video.PoiSearchKeywordResponse;
import io.basc.start.bytedance.video.ShareIdRequest;
import io.basc.start.bytedance.video.ShareIdResponse;
import io.basc.start.bytedance.video.VdeoPartUploadRequest;
import io.basc.start.bytedance.video.VideoCreateRequest;
import io.basc.start.bytedance.video.VideoData;
import io.basc.start.bytedance.video.VideoDataResponse;
import io.basc.start.bytedance.video.VideoListResponse;
import io.basc.start.bytedance.video.VideoPartInitResponse;
import io.basc.start.bytedance.video.VideoPartRequest;
import io.basc.start.bytedance.video.VideoUploadResponse;

public class OpenApi {

	private <R> Map<String, Object> validateAndGetParameterMap(R request) {
		FastValidator.validate(request);
		return MapperUtils.getFields(OauthAccessTokenRequest.class).entity().all().getValueMap(request);
	}

	private TypeDescriptor wrapperResponseType(ResolvableType resposeType) {
		ResolvableType resolvableType = ResolvableType.forClassWithGenerics(Response.class, resposeType);
		return TypeDescriptor.valueOf(resolvableType);
	}

	private <P> P post(GateWay gateWay, String path, Object urlRequest, Object bodyRequest, MediaType mediaType,
			ResolvableType responseType) {
		String pathToUse = path;
		if (urlRequest != null) {
			Map<String, Object> parameterMap = validateAndGetParameterMap(urlRequest);
			pathToUse = UriUtils.appendQueryParams(pathToUse, parameterMap, URLCodec.UTF_8);
		}

		if (bodyRequest != null) {
			FastValidator.validate(bodyRequest);
		}

		HttpResponseEntity<P> responseEntity = HttpUtils.getHttpClient().post(TypeDescriptor.valueOf(responseType),
				gateWay.getUrl() + pathToUse, bodyRequest, mediaType);
		return responseEntity.getBody();
	}

	private <P> P post(GateWay gateWay, String path, Object urlRequest, Object bodyRequest, MediaType mediaType,
			Class<P> responseType) {
		return post(gateWay, path, urlRequest, bodyRequest, mediaType, ResolvableType.forClass(responseType));
	}

	private <P> Response<P> doPost(GateWay gateWay, String path, Object urlRequest, Object bodyRequest,
			MediaType mediaType, ResolvableType responseType) {
		return post(gateWay, path, urlRequest, bodyRequest, mediaType,
				ResolvableType.forClassWithGenerics(Response.class, responseType));
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

	public Response<PagingResponseCodeTotalList<HotsearchTrendingSentences>> hotsearchTrendingSentences(
			ClientPagingRequest request) {
		return doGet(GateWay.DOUYIN, "/hotsearch/trending/sentences/", request, ResolvableType
				.forClassWithGenerics(PagingResponseCodeTotalList.class, HotsearchTrendingSentences.class));
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

	public Response<DiscoveryEntRankItemResponse> discoveryEntRankItem(DiscoveryEntRankItemRequest request) {
		return doGet(GateWay.DOUYIN, "/discovery/ent/rank/item/", request, DiscoveryEntRankItemResponse.class);
	}

	public Response<PagingResponseCodeList<DiscoveryEntRrankVersion>> discoveryEntRrankVersion(
			DiscoveryEntRrankVersionRequest request) {
		return doGet(GateWay.DOUYIN, "/discovery/ent/rank/version/", request,
				ResolvableType.forClassWithGenerics(PagingResponseCodeList.class, DiscoveryEntRrankVersion.class));
	}

	public Response<ResponseCodeResultList<DataExternalSdkShare>> dataExternalSdkShare(DataExternalSdkShare request) {
		return doGet(GateWay.DOUYIN, "/data/external/sdk_share/", request,
				ResolvableType.forClassWithGenerics(ResponseCodeResultList.class, DataExternalSdkShare.class));
	}

	public Response<ResponseCodeResultList<DataExternalAnchorMpItemClickDistribution>> dataExternalAnchorMpItemClickDistribution(
			DataExternalAnchorMpItemClickDistributionRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/anchor/mp_item_click_distribution/", request, ResolvableType
				.forClassWithGenerics(ResponseCodeResultList.class, DataExternalAnchorMpItemClickDistribution.class));
	}

	public Response<ListData<DataExternBillboardHotVideo>> dataExternBillboardHotVideo(ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/billboard/hot_video/", request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternBillboardHotVideo.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardSport(SportRankApiType apiType,
			ClientRequest request) {
		return doGet(GateWay.DOUYIN, apiType.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardAmusement(AmusementRankApiType apiType,
			ClientRequest request) {
		return doGet(GateWay.DOUYIN, apiType.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardGame(GameRankApiType apiType,
			ClientRequest request) {
		return doGet(GateWay.DOUYIN, apiType.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardFood(FoodRankApiType apiType,
			ClientRequest request) {
		return doGet(GateWay.DOUYIN, apiType.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardDramaOverall(ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/billboard/drama/overall/", request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardCar(CarRankApiType apiType,
			ClientRequest request) {
		return doGet(GateWay.DOUYIN, apiType.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardTravelOverall(TravelRankApiType apiType,
			ClientRequest request) {
		return doGet(GateWay.DOUYIN, apiType.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboard>> dataExternBillboardCospa(CospaRankApiType type,
			ClientRequest request) {
		return doGet(GateWay.DOUYIN, type.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternBillboard.class));
	}

	public Response<ListData<DataExternBillboardStars>> dataExternBillboardStars(ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/billboard/stars/", request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternBillboardStars.class));
	}

	public Response<ListData<DataExternBillboardLive>> dataExternBillboardLive(ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/billboard/live/", request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternBillboardLive.class));
	}

	public Response<ListData<DataExternBillboardMusic>> dataExternBillboardMusic(MusicRankApiType type,
			ClientRequest request) {
		return doGet(GateWay.DOUYIN, type.getApi(), request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternBillboardMusic.class));
	}

	public Response<ListData<DataExternBillboardTopic>> dataExternBillboardTopic(ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/billboard/topic/", request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternBillboardTopic.class));
	}

	public Response<ListData<DataExternBillboardProp>> dataExternBillboardProp(ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/data/extern/billboard/prop/", request,
				ResolvableType.forClassWithGenerics(ListData.class, DataExternBillboardProp.class));
	}

	public Response<EnterpriseLeadsUserListResponse> enterpriseLeadsUserList(EnterpriseLeadsUserListRequest request) {
		return doGet(GateWay.DOUYIN, "/enterprise/leads/user/list/", request, EnterpriseLeadsUserListResponse.class);
	}

	public Response<EnterpriseLeadsUserDetailResponse> enterpriseLeadsUserDetail(
			EnterpriseLeadsUserDetailRequest request) {
		return doGet(GateWay.DOUYIN, "/enterprise/leads/user/detail/", request,
				EnterpriseLeadsUserDetailResponse.class);
	}

	public Response<PagingResponseCodeList<EnterpriseLeadsUserAction>> enterpriseLeadsUserActionList(
			EnterpriseLeadsUserActionListRequest request) {
		return doGet(GateWay.DOUYIN, "/enterprise/leads/user/action/list/", request,
				ResolvableType.forClassWithGenerics(PagingResponseCodeList.class, EnterpriseLeadsUserAction.class));
	}

	public Response<PagingResponseCodeList<Tag>> enterpriseLeadsTagList(UserPagingRequest request) {
		return doGet(GateWay.DOUYIN, "/enterprise/leads/tag/list/", request,
				ResolvableType.forClassWithGenerics(PagingResponseCodeList.class, Tag.class));
	}

	public Response<PagingResponseCodeList<String>> enterpriseLeadsTagUserList(
			EnterpriseLeadsTagUserListRequest request) {
		return doGet(GateWay.DOUYIN, "/enterprise/leads/tag/user/list/", request,
				ResolvableType.forClassWithGenerics(PagingResponseCodeList.class, String.class));
	}

	public Response<EnterpriseLeadsTagResponse> enterpriseLeadsTagCreate(UserRequest user, String tag_name) {
		return doPost(GateWay.DOUYIN, "/enterprise/leads/tag/create/", user,
				Collections.singletonMap("tag_name", tag_name), MediaType.APPLICATION_JSON,
				EnterpriseLeadsTagResponse.class);
	}

	public Response<EnterpriseLeadsTagResponse> enterpriseLeadsTagUpdate(UserRequest user, Tag tag) {
		return doPost(GateWay.DOUYIN, "/enterprise/leads/tag/update/", user, tag, MediaType.APPLICATION_JSON,
				EnterpriseLeadsTagResponse.class);
	}

	public Response<ResponseCode> enterpriseLeadsTagDelete(UserRequest user, String tag_id) {
		return doPost(GateWay.DOUYIN, "/enterprise/leads/tag/delete/", user, Collections.singletonMap("tag_id", tag_id),
				MediaType.APPLICATION_JSON, ResponseCode.class);
	}

	public Response<ResponseCode> enterpriseLeadsTagUserUpdate(UserRequest user,
			EnterpriseLeadsTagUserUpdateRequest request) {
		return doPost(GateWay.DOUYIN, "/enterprise/leads/tag/user/update/", user, request, MediaType.APPLICATION_JSON,
				ResponseCode.class);
	}

	public Response<EnterpriseImCardSaveResponse> enterpriseImCardSave(UserRequest user,
			EnterpriseImCardSaveRequest request) {
		return doPost(GateWay.DOUYIN, "/enterprise/im/card/save/", user, request, MediaType.APPLICATION_JSON,
				EnterpriseImCardSaveResponse.class);
	}

	public Response<EnterpriseImCardListResponse> enterpriseImCardList(UserPagingRequest request) {
		return doGet(GateWay.DOUYIN, "/enterprise/im/card/list/", request, EnterpriseImCardListResponse.class);
	}

	public Response<ResponseCode> enterpriseImCardDelete(UserRequest user, EnterpriseImCardDeleteRequest request) {
		return doPost(GateWay.DOUYIN, "/enterprise/im/card/delete/", user, request, MediaType.APPLICATION_JSON,
				ResponseCode.class);
	}

	public Response<PoiSupplierSyncResponse> poiSupplierSync(ClientRequest client, PoiSupplier request) {
		return doPost(GateWay.DOUYIN, "/poi/supplier/sync/", client, request, MediaType.APPLICATION_JSON,
				PoiSupplierSyncResponse.class);
	}

	public Response<PoiSupplierQueryResponse> poiSupplierQuery(PoiSupplierQueryRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/supplier/query/", request, PoiSupplierQueryResponse.class);
	}

	public Response<PoiQueryResponse> poiQuery(PoiQueryRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/query/", request, PoiQueryResponse.class);
	}

	public Response<MatchResultList<PoiV2SupplierQueryTask>> poiV2SupplierQueryTask(
			PoiV2SupplierQueryTaskRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/v2/supplier/query/task/", request,
				ResolvableType.forClassWithGenerics(MatchResultList.class, PoiV2SupplierQueryTask.class));
	}

	public Response<MatchResultList<PoiV2SupplierQuerySupplier>> poiV2SupplierQuerySupplier(
			PoiSupplierQueryRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/v2/supplier/query/supplier/", request,
				ResolvableType.forClassWithGenerics(MatchResultList.class, PoiV2SupplierQuerySupplier.class));
	}

	public Response<PoiV2SupplierMatchResponse> poiV2SupplierMatch(ClientRequest client,
			PoiV2SupplierMatchRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/v2/supplier/match/", client, request, MediaType.APPLICATION_JSON,
				PoiV2SupplierMatchResponse.class);
	}

	public Response<PoiSpuQueryResponse> poiSpuQuery(PoiSpuQueryRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/spu/query/", request, PoiSpuQueryResponse.class);
	}

	public Response<ResponseCode> poiSkuSync(ClientRequest client, PoiSkuSyncRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/sku/sync/", client, request, MediaType.APPLICATION_JSON,
				ResponseCode.class);
	}

	public Response<PoiExtHotelSku> poiExtHotelSku(PoiExtHotelSkuRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/ext/hotel/sku/", request, PoiExtHotelSku.class);
	}

	public Response<PoiV2SpuSyncResponse> poiV2SpuSync(ClientRequest client, PoiV2SpuSyncRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/v2/spu/sync/", client, request, MediaType.APPLICATION_JSON,
				PoiV2SpuSyncResponse.class);
	}

	public Response<PoiV2SpuStatusSyncResponse> poiV2SpuStatusSync(ClientRequest client,
			PoiV2SpuStatusSyncRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/v2/spu/status_sync/", client, request, MediaType.APPLICATION_JSON,
				PoiV2SpuStatusSyncResponse.class);
	}

	public Response<PoiV2SpuStockUpdateResponse> poiV2SpuStockUpdate(ClientRequest client,
			PoiV2SpuStockUpdateRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/v2/spu/stock_update/", client, request, MediaType.APPLICATION_JSON,
				PoiV2SpuStockUpdateResponse.class);
	}

	public Response<PoiV2SpuStatusResponse> poiV2SpuStatus(PoiV2SpuStatusRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/v2/spu/status/", request, PoiV2SpuStatusResponse.class);
	}

	public Response<PoiV2SpuGetResponse> poiV2SpuGet(PoiV2SpuGetRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/v2/spu/get/", request, PoiV2SpuGetResponse.class);
	}

	public Response<PoiV2SpuTakeRateSyncResponse> poiV2SpuTakeRateSync(ClientRequest client,
			PoiV2SpuTakeRateSyncRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/v2/spu/take_rate/sync/", client, request, MediaType.APPLICATION_JSON,
				PoiV2SpuTakeRateSyncResponse.class);
	}

	public Response<ResponseCode> poiOrderStatus(ClientRequest client, PoiOrderStatusRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/order/status/", client, request, MediaType.APPLICATION_JSON,
				ResponseCode.class);
	}

	public PoiExtHotelOrderCommitResponse poiExtHotelOrderCommit(ClientRequest client,
			PoiExtHotelOrderCommitRequest request) {
		return post(GateWay.DOUYIN, "/poi/ext/hotel/order/commit/", client, request, MediaType.APPLICATION_JSON,
				PoiExtHotelOrderCommitResponse.class);
	}

	public PoiExtHotelOrderStatusResponse poiExtHotelOrderStatus(PoiExtHotelOrderStatusRequest request) {
		return post(GateWay.DOUYIN, "/poi/ext/hotel/order/status/", null, request, MediaType.APPLICATION_JSON,
				PoiExtHotelOrderStatusResponse.class);
	}

	public PoiExtHotelOrderCancelResponse poiExtHotelOrderCancel(PoiExtHotelOrderCancelRequest request) {
		return post(GateWay.DOUYIN, "/poi/ext/hotel/order/cancel/", null, request, MediaType.APPLICATION_JSON,
				PoiExtHotelOrderCancelResponse.class);
	}

	public PoiOrderConfirmResponse poiOrderConfirm(ClientRequest client, PoiOrderConfirmRequest request) {
		return post(GateWay.DOUYIN, "/poi/order/confirm/", client, request, MediaType.APPLICATION_JSON,
				PoiOrderConfirmResponse.class);
	}

	public Response<PoiExtPresaleGrouponOrderCreateResponse> poiExtPresaleGrouponOrderCreate(
			PoiExtPresaleGrouponOrderCreateRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/ext/presale_groupon/order/create/", null, request,
				MediaType.APPLICATION_JSON, PoiExtPresaleGrouponOrderCreateResponse.class);
	}

	public Response<PoiExtPresaleGrouponOrder> poiExtPresaleGrouponOrderCommit(
			PoiExtPresaleGrouponOrderCommitRequest request) {
		return doPost(GateWay.DOUYIN, "/poi/ext/presale_groupon/order/commit/", null, request,
				MediaType.APPLICATION_JSON, PoiExtPresaleGrouponOrder.class);
	}

	public Response<PoiExtPresaleGrouponOrder> poiExtPresaleGrouponOrderCancel(PoiExtPresaleGrouponOrder request) {
		return doPost(GateWay.DOUYIN, "/poi/ext/presale_groupon/order/cancel/", null, request,
				MediaType.APPLICATION_JSON, PoiExtPresaleGrouponOrder.class);
	}

	public Response<PoiOrderBillToken> poiOrderBillToken(PoiOrderBillTokenRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/order/bill/token/", request, PoiOrderBillToken.class);
	}

	public Response<PoiOrderListToken> poiOrderListToken(PoiOrderListTokenRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/order/list/token/", request, PoiOrderListToken.class);
	}

	public Response<PoiOrderSyncResponse> poiOrderSync(ClientRequest client, PoiOrderSyncRequest request) {
		return doPost(GateWay.DOUYIN, " /poi/order/sync/", client, request, MediaType.APPLICATION_JSON,
				PoiOrderSyncResponse.class);
	}

	public Response<ResponseCodeResultList<DataExternalPoiBaseResult>> dataExternalPoiBase(
			DataExternalPoiBaseRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/poi/base/", request,
				ResolvableType.forClassWithGenerics(ResponseCodeResultList.class, DataExternalPoiBaseResult.class));
	}

	public Response<DataExternalPoiUser> dataExternalPoiUser(DataExternalPoiUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/poi/user/", request, DataExternalPoiUser.class);
	}

	public Response<ResponseCodeResultList<DataExternalPoiServiceBaseResult>> dataExternalPoiServiceBase(
			DataExternalPoiServiceBaseRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/poi/service_base/", request, ResolvableType
				.forClassWithGenerics(ResponseCodeResultList.class, DataExternalPoiServiceBaseResult.class));
	}

	public Response<DataExternalPoiUser> dataExternalPoiServiceUser(DataExternalPoiServiceUserRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/poi/service_user/", request, DataExternalPoiUser.class);
	}

	public Response<ResponseCodeResultList<DataExternalPoiBillboardResult>> dataExternalPoiBillboard(
			DataExternalPoiBillboardRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/poi/billboard/", request, ResolvableType
				.forClassWithGenerics(ResponseCodeResultList.class, DataExternalPoiBillboardResult.class));
	}

	public Response<PagingResponseCodeTotalList<DataExternalPoiClaim>> dataExternalPoiClaimList(
			DataExternalPoiClaimListRequest request) {
		return doGet(GateWay.DOUYIN, "/data/external/poi/claim/list/", request,
				ResolvableType.forClassWithGenerics(PagingResponseCodeTotalList.class, DataExternalPoiClaim.class));
	}

	public Response<PoiBaseQueryAmapResponse> poiBaseQueryAmap(PoiBaseQueryAmapRequest request) {
		return doGet(GateWay.DOUYIN, "/poi/base/query/amap/", request, PoiBaseQueryAmapResponse.class);
	}

	public Response<DevtoolMicappIsLegalResponse> devtoolMicappIsLegal(DevtoolMicappIsLegalRequest request) {
		return doGet(GateWay.DOUYIN, "/devtool/micapp/is_legal/", request, DevtoolMicappIsLegalResponse.class);
	}

	public Response<ResponseCode> sandboxWebhookEventSend(ClientRequest client,
			SandboxWebhookEventSendRequest request) {
		return doPost(GateWay.DOUYIN, "/sandbox/webhook/event/send/", client, request, MediaType.APPLICATION_JSON,
				ResponseCode.class);
	}

	public Response<JsGetticketResponse> jsGetticket(ClientRequest request) {
		return doGet(GateWay.DOUYIN, "/js/getticket/", request, JsGetticketResponse.class);
	}
}
