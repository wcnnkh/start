package io.basc.platform.integration.bytedance.user;

import io.basc.platform.integration.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class UserinfoResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(example = "上海")
	private String province;
	@Schema(description = "用户在当前开发者账号下的唯一标识（未绑定开发者账号没有该字段）", example = "1ad4e099-4a0c-47d1-a410-bffb4f2f64a4")
	private String union_id;
	@Schema(example = "https://example.com/x.jpeg")
	private String avatar;
	@Schema(description = "类型: * `EAccountM` - 普通企业号 * `EAccountS` - 认证企业号 * `EAccountK` - 品牌企业号")
	private String e_account_role;
	@Schema(description = "用户在当前应用的唯一标识", example = "0da22181-d833-447f-995f-1beefea5bef3")
	private String open_id;
	@Schema(example = "张伟")
	private String nickname;
	@Schema(example = "上海")
	private String city;
	@Schema(example = "中国")
	private String country;
	@Schema(description = "性别: * `0` - 未知 * `1` - 男性 * `2` - 女性")
	private Long gender;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getUnion_id() {
		return union_id;
	}

	public void setUnion_id(String union_id) {
		this.union_id = union_id;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getE_account_role() {
		return e_account_role;
	}

	public void setE_account_role(String e_account_role) {
		this.e_account_role = e_account_role;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Long getGender() {
		return gender;
	}

	public void setGender(Long gender) {
		this.gender = gender;
	}
}
