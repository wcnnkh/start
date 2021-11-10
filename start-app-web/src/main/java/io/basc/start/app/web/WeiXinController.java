package io.basc.start.app.web;

import io.basc.framework.beans.annotation.Autowired;
import io.basc.framework.context.result.DataResult;
import io.basc.framework.context.result.Result;
import io.basc.framework.context.result.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.security.session.UserSession;
import io.basc.framework.util.StringUtils;
import io.basc.framework.web.pattern.annotation.RequestMapping;
import io.basc.start.app.enums.SexType;
import io.basc.start.app.user.enums.AccountType;
import io.basc.start.app.user.enums.UnionIdType;
import io.basc.start.app.user.model.UserAttributeModel;
import io.basc.start.app.user.pojo.User;
import io.basc.start.app.user.security.LoginRequired;
import io.basc.start.app.user.security.UserLoginService;
import io.basc.start.app.user.service.UserService;
import io.basc.start.tencent.wx.Scope;
import io.basc.start.tencent.wx.UserAccessToken;
import io.basc.start.tencent.wx.UserGrantClient;
import io.basc.start.tencent.wx.Userinfo;
import io.basc.start.tencent.wx.WeiXinUtils;
import io.basc.start.tencent.wx.miniprogram.PhoneNumber;
import io.basc.start.tencent.wx.miniprogram.Session;
import io.basc.start.tencent.wx.miniprogram.WeiXinMiniprogramUtils;
import io.basc.start.tencent.wx.miniprogram.WeixinMiniprogram;

import java.util.Map;

@RequestMapping(value = "weixin", methods = { HttpMethod.GET, HttpMethod.POST })
@io.basc.framework.mvc.annotation.FactoryResult
public class WeiXinController {
	public static final String WX_XCX_SESSION_KEY = "wx.xcx.session.key";
	
	@Autowired(required=false)
	private UserGrantClient userGrantClient;
	private UserService userService;
	@Autowired
	private ResultFactory resultFactory;
	@Autowired
	private UserLoginService userControllerService;
	@Autowired(required=false)
	private WeixinMiniprogram weixinMiniprogram;
	
	public WeiXinController(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "login")
	public Result login(String code, Scope scope, HttpChannel httpChannel) {
		if(userGrantClient == null){
			return resultFactory.error("暂不支持微信登录");
		}
		
		if (StringUtils.isEmpty(code)) {
			return resultFactory.parameterError();
		}

		UserAccessToken userAccessToken = userGrantClient.getAccessToken(code, null);
		User user = userService.getUserByUnionId(userAccessToken.getOpenid(), UnionIdType.WX_OPENID);
		if (user == null) {
			UserAttributeModel userAttributeModel = new UserAttributeModel();
			if (scope == Scope.USERINFO) {
				Userinfo userinfo = WeiXinUtils.getUserinfo(userAccessToken.getOpenid(),
						userAccessToken.getToken().getToken());
				userAttributeModel.setSex(SexType.forValue(userinfo.getSex()));
				userAttributeModel.setNickname(userinfo.getNickname());
				if (StringUtils.isNotEmpty(userinfo.getHeadimgurl())) {
					userAttributeModel.setHeadImg(StringUtils.split(userinfo.getHeadimgurl(), ",").findFirst().get().toString());
				}
			}

			DataResult<User> result = userService.registerUnionId(UnionIdType.WX_OPENID, userAccessToken.getOpenid(),
					null, userAttributeModel);
			if (result.isError()) {
				return result;
			}
			user = result.getData();
		}

		Map<String, Object> infoMap = userControllerService.login(user, httpChannel);
		return resultFactory.success(infoMap);
	}

	@LoginRequired
	@RequestMapping(value = "bind")
	public Result bind(long uid, String code, Scope scope) {
		if(userGrantClient == null){
			return resultFactory.error("暂不支持微信绑定");
		}
		
		UserAccessToken userAccessToken = userGrantClient.getAccessToken(code, null);
		User user = userService.getUserByUnionId(userAccessToken.getOpenid(), UnionIdType.WX_OPENID);
		if (user == null) {
			return resultFactory.error("未注册，无法绑定");
		}
		
		Result result = userService.bindUnionId(uid, UnionIdType.WX_OPENID, userAccessToken.getOpenid());
		if(result.isError()){
			return result;
		}

		UserAttributeModel userAttributeModel = new UserAttributeModel();
		if (scope == Scope.USERINFO) {
			Userinfo userinfo = WeiXinUtils.getUserinfo(userAccessToken.getOpenid(),
					userAccessToken.getToken().getToken());
			userAttributeModel.setSex(SexType.forValue(userinfo.getSex()));
			userAttributeModel.setNickname(userinfo.getNickname());
			userAttributeModel.setHeadImg(StringUtils.split(userinfo.getHeadimgurl(), ",").findFirst().get().toString());
			return userService.updateUserAttribute(uid, userAttributeModel);
		}
		
		return resultFactory.success(user);
	}
	
	@RequestMapping(value="/xcx/login")
	public Result xcx_login(String code, HttpChannel httpChannel){
		if(weixinMiniprogram == null){
			return resultFactory.error("暂不支持小程序登录");
		}
		
		Session session = weixinMiniprogram.code2session(code);
		if(session.isError()){
			return resultFactory.error(session.getErrmsg());
		}

		User user = userService.getUserByUnionId(session.getOpenid(), UnionIdType.WX_XCX_OPENID);
		if(user == null){
			//不存在，注册一下
			DataResult<User> dataResult = userService.registerUnionId(UnionIdType.WX_XCX_OPENID, session.getOpenid(), null, null);
			if(dataResult.isError()){
				return dataResult;
			}
			
			user = dataResult.getData();
		}
		
		Map<String, Object> result = userControllerService.login(user, httpChannel);
		UserSession<Long> userSession = httpChannel.getUserSession(Long.class);
		if(userSession != null){
			userSession.setAttribute(WX_XCX_SESSION_KEY, session.getSession_key());
		}
		return resultFactory.success(result);
	}
	
	@LoginRequired
	@RequestMapping(value="/xcx/getPhoneNumber")
	public Result xcx_bind(String encryptedData, String iv, UserSession<Long> userSession){
		if(StringUtils.isEmpty(encryptedData, iv)){
			return resultFactory.parameterError();
		}
		
		String sessionKey = (String) userSession.getAttribute(WX_XCX_SESSION_KEY);
		if(sessionKey == null){
			return resultFactory.error("系统错误(sessionKey)");
		}
		
		PhoneNumber phoneNumber = WeiXinMiniprogramUtils.decrypt(encryptedData, sessionKey, iv);
		DataResult<User> dataResult = userService.bind(userSession.getUid(), AccountType.PHONE, phoneNumber.getPhoneNumber());
		if(dataResult.isError()){
			return dataResult;
		}

		return resultFactory.success();
	}
}
