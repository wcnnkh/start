package scw.integration.bytedance.enterprise;

import java.io.Serializable;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class EnterpriseUser implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "性别: * `0` - 未知 * `1` - 男性 * `2` - 女性")
	private Long gender;
	@Schema(description = "用户状态 * `-1` - 没兴趣 * `0` - 了解 * `1` - 有兴趣 * `2` - 有意愿 * `10` - 已转化")
	private Long leads_level;
	private String telephone;
	@Schema(example = "https://example.com/x.jpeg")
	private String avatar;
	@Schema(example = "上海")
	private String city;
	@Schema(description = "当前指定用户是否已关注本企业号", example = "true")
	private Boolean is_follow;
	@Schema(example = "张伟")
	private String nickname;
	@Schema(description = "用户在当前应用的唯一标识", example = "0da22181-d833-447f-995f-1beefea5bef3")
	private String open_id;
	@Schema(description = "绑定的标签列表")
	private List<Tag> tag_list;
	private String wechat;
	private Long age;

	public Long getGender() {
		return gender;
	}

	public void setGender(Long gender) {
		this.gender = gender;
	}

	public Long getLeads_level() {
		return leads_level;
	}

	public void setLeads_level(Long leads_level) {
		this.leads_level = leads_level;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getIs_follow() {
		return is_follow;
	}

	public void setIs_follow(Boolean is_follow) {
		this.is_follow = is_follow;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public List<Tag> getTag_list() {
		return tag_list;
	}

	public void setTag_list(List<Tag> tag_list) {
		this.tag_list = tag_list;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}
}
