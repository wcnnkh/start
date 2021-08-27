package io.basc.platform.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class RankMetadata extends Rank {
	private static final long serialVersionUID = 1L;
	@Schema(description = "昵称", example = "昵称")
	private String nickname;
	@Schema(description = "头像", example = "https://example.com/x.jpeg")
	private String avatar;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
}
