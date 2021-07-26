package scw.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternBillboardLive extends Rank {
	private static final long serialVersionUID = 1L;
	@Schema(description = "直播封面", example = "https://example.com/x.jpeg")
	private String cover;
	@Schema(description = "直播标题", example = "测试标题")
	private String title;
	@Schema(description = "昵称", example = "昵称")
	private String nickname;
	@Schema(description = "热度指数", example = "10000")
	private Double hot_value;

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Double getHot_value() {
		return hot_value;
	}

	public void setHot_value(Double hot_value) {
		this.hot_value = hot_value;
	}
}
