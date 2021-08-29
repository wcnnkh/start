package io.basc.integration.bytedance.video;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "小程序标题", example = "小程序标题")
	private String micro_app_title;
	@Schema(description = "开发者在小程序中生成该页面时写的path地址", example = "pages/xxx/xxx")
	private String micro_app_url;
	@Schema(description = "地理位置id，poi_id可通过\"查询POI信息\"能力获取")
	private String poi_id;
	@Schema(description = "地理位置名称")
	private String poi_name;
	@Schema(description = "标题，可以带话题。 如title1#话题1 #话题2 注意：话题审核依旧遵循抖音的审核逻辑，强烈建议第三方谨慎拟定话题名称，避免强导流行为。", example = "itle1#话题1 #话题2 @openid1")
	private String text;
	@Schema(description = "如果需要at其他用户。将text中@nickname对应的open_id放到这里。")
	private String[] at_users;
	@Schema(description = "小程序id", example = "ttef9b112671b152ba")
	private String micro_app_id;

	public String getMicro_app_title() {
		return micro_app_title;
	}

	public void setMicro_app_title(String micro_app_title) {
		this.micro_app_title = micro_app_title;
	}

	public String getMicro_app_url() {
		return micro_app_url;
	}

	public void setMicro_app_url(String micro_app_url) {
		this.micro_app_url = micro_app_url;
	}

	public String getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(String poi_id) {
		this.poi_id = poi_id;
	}

	public String getPoi_name() {
		return poi_name;
	}

	public void setPoi_name(String poi_name) {
		this.poi_name = poi_name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String[] getAt_users() {
		return at_users;
	}

	public void setAt_users(String[] at_users) {
		this.at_users = at_users;
	}

	public String getMicro_app_id() {
		return micro_app_id;
	}

	public void setMicro_app_id(String micro_app_id) {
		this.micro_app_id = micro_app_id;
	}
}
