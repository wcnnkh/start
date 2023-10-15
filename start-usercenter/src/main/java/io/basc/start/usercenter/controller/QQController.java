package io.basc.start.usercenter.controller;

import java.util.Map;

import io.basc.framework.context.annotation.Autowired;
import io.basc.framework.context.transaction.DataResult;
import io.basc.framework.context.transaction.Result;
import io.basc.framework.context.transaction.ResultFactory;
import io.basc.framework.http.HttpMethod;
import io.basc.framework.mvc.HttpChannel;
import io.basc.framework.mvc.result.FactoryResult;
import io.basc.framework.security.Token;
import io.basc.framework.util.StringUtils;
import io.basc.framework.web.pattern.annotation.RequestMapping;
import io.basc.start.tencent.qq.connect.QQ;
import io.basc.start.tencent.qq.connect.QQRequest;
import io.basc.start.tencent.qq.connect.UserInfoResponse;
import io.basc.start.usercenter.enums.SexType;
import io.basc.start.usercenter.enums.UnionIdType;
import io.basc.start.usercenter.model.UserAttributeModel;
import io.basc.start.usercenter.pojo.User;
import io.basc.start.usercenter.security.LoginRequired;
import io.basc.start.usercenter.security.UserLoginService;
import io.basc.start.usercenter.service.UserService;

@RequestMapping(value = "/usercenter/qq", methods = { HttpMethod.GET, HttpMethod.POST })
@FactoryResult
public class QQController {
	private QQ qq;
	private UserService userService;
	@Autowired
	private ResultFactory resultFactory;
	@Autowired
	private UserLoginService userControllerService;

	public QQController(UserService userService, QQ qq) {
		this.userService = userService;
		this.qq = qq;
	}

	@RequestMapping(value = "login")
	public Result login(String openid, String accessToken, HttpChannel httpChannel) {
		if (StringUtils.isAnyEmpty(openid, accessToken)) {
			return resultFactory.parameterError();
		}

		User user = userService.getUserByUnionId(openid, UnionIdType.QQ_OPENID);
		if (user == null) {
			UserInfoResponse userinfo = qq.getUserinfo(new QQRequest(accessToken, openid));
			UserAttributeModel userAttributeModel = new UserAttributeModel();
			userAttributeModel.setSex(SexType.forDescribe(userinfo.getGender()));
			userAttributeModel.setHeadImg(userinfo.getfigureUrlQQ1());
			userAttributeModel.setNickname(userinfo.getNickname());
			DataResult<User> dataResult = userService.registerUnionId(UnionIdType.QQ_OPENID, openid, null,
					userAttributeModel);
			if (dataResult.isError()) {
				return dataResult;
			}

			user = dataResult.getData();
		}
		Map<String, Object> infoMap = userControllerService.login(user, httpChannel);
		return resultFactory.success(infoMap);
	}

	@RequestMapping(value = "web_login")
	public Result webLogin(String code, String redirect_uri, HttpChannel httpChannel) {
		if (StringUtils.isAnyEmpty(code, redirect_uri)) {
			return resultFactory.parameterError();
		}

		Token token = qq.getToken(redirect_uri, code);
		String openid = qq.getOpenid(token.getToken());
		return login(openid, token.getToken(), httpChannel);
	}

	@LoginRequired
	@RequestMapping(value = "bind")
	public Result bind(long uid, String openid, String accessToken) {
		if (StringUtils.isAnyEmpty(openid, accessToken)) {
			return resultFactory.parameterError();
		}

		User user = userService.getUserByUnionId(openid, UnionIdType.QQ_OPENID);
		if (user == null) {
			return resultFactory.error("用户不存在");
		}

		Result result = userService.bindUnionId(uid, UnionIdType.QQ_OPENID, openid);
		if (result.isError()) {
			return result;
		}

		UserInfoResponse userinfo = qq.getUserinfo(new QQRequest(accessToken, openid));
		UserAttributeModel userAttributeModel = new UserAttributeModel();
		userAttributeModel.setSex(SexType.forDescribe(userinfo.getGender()));
		userAttributeModel.setHeadImg(userinfo.getfigureUrlQQ1());
		userAttributeModel.setNickname(userinfo.getNickname());
		return userService.updateUserAttribute(uid, userAttributeModel);
	}

	@RequestMapping(value = "web_bind")
	@LoginRequired
	public Result webBind(long uid, String code, String redirect_uri) {
		if (StringUtils.isAnyEmpty(code, redirect_uri)) {
			return resultFactory.parameterError();
		}

		Token token = qq.getToken(redirect_uri, code);
		String openid = qq.getOpenid(token.getToken());
		return bind(uid, openid, token.getToken());
	}
}
