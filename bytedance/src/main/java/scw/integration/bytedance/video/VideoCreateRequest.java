package scw.integration.bytedance.video;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class VideoCreateRequest extends CreateRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "video_id, 通过/video/upload/接口得到。注意每次调用/video/create/都要调用/video/upload/生成新的video_id", example = "v0201f510000smhdsr0ggl1v4a2b2ps1", required = true)
	@NotNull
	private String video_id;
	@Schema(description = "文章ID，暂不开放")
	private String article_id;
	@Schema(description = "文章自定义标题，暂不开放")
	private String article_title;
	@Schema(description = "时效新闻标签，1表示使用。暂不开放")
	private Integer timeliness_label;
	@Schema(description = "最多可添加3个，用|隔开。暂不开放")
	private String timeliness_keyword;
	@Schema(description = "游戏id。暂不开放")
	private String game_id;
	@Schema(description = "游戏个性化参数")
	private String game_content;
	@Schema(description = "将传入的指定时间点对应帧设置为视频封面（单位：秒）", example = "2.3")
	private Double cover_tsp;
	@Schema(description = "自定义封面图片,参数为接口/image/upload/ 返回的image_id")
	private String custom_cover_image_url;

	public String getVideo_id() {
		return video_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	public String getArticle_id() {
		return article_id;
	}

	public void setArticle_id(String article_id) {
		this.article_id = article_id;
	}

	public String getArticle_title() {
		return article_title;
	}

	public void setArticle_title(String article_title) {
		this.article_title = article_title;
	}

	public Integer getTimeliness_label() {
		return timeliness_label;
	}

	public void setTimeliness_label(Integer timeliness_label) {
		this.timeliness_label = timeliness_label;
	}

	public String getTimeliness_keyword() {
		return timeliness_keyword;
	}

	public void setTimeliness_keyword(String timeliness_keyword) {
		this.timeliness_keyword = timeliness_keyword;
	}

	public String getGame_id() {
		return game_id;
	}

	public void setGame_id(String game_id) {
		this.game_id = game_id;
	}

	public String getGame_content() {
		return game_content;
	}

	public void setGame_content(String game_content) {
		this.game_content = game_content;
	}

	public Double getCover_tsp() {
		return cover_tsp;
	}

	public void setCover_tsp(Double cover_tsp) {
		this.cover_tsp = cover_tsp;
	}

	public String getCustom_cover_image_url() {
		return custom_cover_image_url;
	}

	public void setCustom_cover_image_url(String custom_cover_image_url) {
		this.custom_cover_image_url = custom_cover_image_url;
	}
}
