package scw.integration.bytedance.search;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.video.VideoMetadata;

public class VideoSearchInfo extends VideoMetadata {
	private static final long serialVersionUID = 1L;
	@Schema(description = "昵称")
	private String nickname;
	@Schema(description = "作者openID")
	private String open_id;
	@Schema(description = "头像")
	private String avatar;
	@Schema(description = "特殊加密的视频id通过用户视频搜索的评论接口获取到")
	private String sec_item_id;

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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSec_item_id() {
		return sec_item_id;
	}

	public void setSec_item_id(String sec_item_id) {
		this.sec_item_id = sec_item_id;
	}
}
