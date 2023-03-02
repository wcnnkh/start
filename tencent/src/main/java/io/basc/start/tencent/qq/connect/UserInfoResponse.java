package io.basc.start.tencent.qq.connect;

import io.basc.framework.json.JsonObject;

/**
 * 
 * https://wiki.connect.qq.com/get_user_info
 * 
 * @author wcnnkh
 *
 */
public class UserInfoResponse extends QQResponse {

	public UserInfoResponse(JsonObject target) {
		super(target);
	}

	/**
	 * 用户在QQ空间的昵称。
	 * 
	 * @return 昵称
	 */
	public String getNickname() {
		return getAsString("nickname");
	}

	/**
	 * 大小为30×30像素的QQ空间头像URL。
	 * 
	 * @return 头像URL
	 */
	public String getFigureUrl() {
		return getAsString("figureurl");
	}

	/**
	 * 大小为50×50像素的QQ空间头像URL。
	 * 
	 * @return 头像URL
	 */
	public String getFigureUrl1() {
		return getAsString("figureurl_1");
	}

	/**
	 * 大小为100×100像素的QQ空间头像URL。
	 * 
	 * @return 头像URL
	 */
	public String getFigureUrl2() {
		return getAsString("figureurl_2");
	}

	/**
	 * 大小为40×40像素的QQ头像URL。
	 * 
	 * @return 头像URL
	 */
	public String getfigureUrlQQ1() {
		return getAsString("figureurl_qq_1");
	}

	/**
	 * 大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100x100的头像，但40x40像素则是一定会有。
	 * 
	 * @return 头像URL
	 */
	public String getFigureurlQQ2() {
		return getAsString("figureurl_qq_2");
	}

	/**
	 * 性别。 如果获取不到则默认返回"男"
	 * 
	 * @return 性别
	 */
	public String getGender() {
		return getAsString("gender");
	}
}
