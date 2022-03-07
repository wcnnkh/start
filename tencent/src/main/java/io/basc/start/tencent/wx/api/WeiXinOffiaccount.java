package io.basc.start.tencent.wx.api;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.basc.framework.http.MediaType;
import io.basc.framework.json.JsonObject;
import io.basc.framework.oauth2.AccessToken;
import io.basc.framework.security.Token;
import io.basc.framework.util.stream.Processor;

public class WeiXinOffiaccount extends ClusterWeiXinApi {

	public WeiXinOffiaccount(String appid, String appsecret) {
		super(appid, appsecret);
	}

	public final Token getJsapiTicket(boolean forceUpdate) throws WeiXinApiException {
		return getTicket("jsapi", forceUpdate);
	}

	/**
	 * 直接从服务器获取
	 * 
	 * @param access_token
	 * @return
	 * @throws WeiXinApiException
	 */
	public final Token getJsapiTicket(String access_token) throws WeiXinApiException {
		return getTicket(access_token, "jsapi");
	}

	public final <T, E extends Throwable> T processWithJsapiTicket(Processor<Token, T, E> processor)
			throws WeiXinApiException, E {
		return processWithTicket("jsapi", processor);
	}

	private static AccessToken parseAccessToken(JsonObject json, String type) {
		return new AccessToken(
				new Token(json.getString("access_token"), json.getIntValue("expires_in"), TimeUnit.SECONDS), type,
				new Token(json.getString("refresh_token"), 30, TimeUnit.DAYS), json.getString("scope"), null);
	}

	public UserAccessToken getUserAccessToken(String code) throws WeiXinApiException {
		Map<String, String> map = new HashMap<String, String>(4, 1);
		map.put("appid", getAppid());
		map.put("secret", getAppsecret());
		map.put("code", code);
		map.put("grant_type", "authorization_code");
		JsonObject json = doPost("https://api.weixin.qq.com/sns/oauth2/access_token", map, MediaType.APPLICATION_FORM_URLENCODED);
		return new UserAccessToken(parseAccessToken(json, "authorization_code"), json.getString("openid"));
	}

	public UserAccessToken refreshUserAccessToken(String refresh_token) throws WeiXinApiException {
		Map<String, String> map = new HashMap<String, String>(4, 1);
		map.put("appid", getAppid());
		map.put("grant_type", "refresh_token");
		map.put("refresh_token", refresh_token);
		JsonObject json = doPost("https://api.weixin.qq.com/sns/oauth2/refresh_token", map, MediaType.APPLICATION_FORM_URLENCODED);
		return new UserAccessToken(parseAccessToken(json, "refresh_token"), json.getString("openid"));
	}

	/**
	 * 获取用户信息
	 * 
	 * @param openid
	 * @param user_access_token
	 * @param lang
	 * @return
	 * @throws WeiXinApiException
	 */
	public Userinfo getUserinfo(String openid, String user_access_token, String lang) throws WeiXinApiException {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("access_token", user_access_token);
		paramMap.put("openid", openid);
		paramMap.put("lang", lang);
		JsonObject json = doPost("https://api.weixin.qq.com/sns/userinfo", paramMap, MediaType.APPLICATION_FORM_URLENCODED);
		return new Userinfo(json.getString("openid"), json.getString("nickname"), json.getIntValue("sex"),
				json.getString("province"), json.getString("city"), json.getString("country"),
				json.getString("headimgurl"), json.getString("privilege"), json.getString("unionid"));
	}

	/**
	 * @see #getUserinfo(String, String, String) 使用zh_CN获取用户信息
	 * @param openid
	 * @param user_access_token
	 * @return
	 * @throws WeiXinApiException
	 */
	public final Userinfo getUserinfo(String openid, String user_access_token) throws WeiXinApiException {
		return getUserinfo(openid, user_access_token, "zh_CN");
	}

	public final Userinfo getUserinfo(UserAccessToken userAccessToken) throws WeiXinApiException {
		return getUserinfo(userAccessToken.getOpenid(), userAccessToken.getToken().getToken());
	}
}
