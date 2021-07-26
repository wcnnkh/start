package scw.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternBillboardHotVideo extends Rank {
	private static final long serialVersionUID = 1L;
	@Schema(description = "视频播放页面。视频播放页可能会失效，请在观看视频前调用/video/data/获取最新的播放页。", example = "https://www.iesdouyin.com/share/video/QDlWd0EzdWVMU2Q0aU5tKzVaOElvVU03ODBtRHFQUCtLUHBSMHFRT21MVkFYYi9UMDYwemRSbVlxaWczNTd6RUJRc3MrM2hvRGlqK2EwNnhBc1lGUkpRPT0=/?region=CN&mid=6753173704399670023&u_code=12h9je425&titleType=title")
	private String share_url;
	@Schema(description = "视频标题", example = "测试视频 #测试话题 @抖音小助手")
	private String title;
	@Schema(description = "视频发布者", example = "昵称")
	private String author;
	@Schema(description = "播放数，离线数据（统计前一日数据）", example = "300")
	private Long play_count;
	@Schema(description = "点赞数，离线数据（统计前一日数据）", example = "200")
	private Long digg_count;
	@Schema(description = "评论数，离线数据（统计前一日数据）", example = "100")
	private Long comment_count;
	@Schema(description = "视频热词（以,隔开）", example = "手机,视频,热门")
	private String hot_words;
	@Schema(description = "热度指数", example = "10000")
	private Double hot_value;
	@Schema(description = "视频封面图", example = "https://example.com/x.jpeg")
	private String item_cover;

	public String getShare_url() {
		return share_url;
	}

	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getPlay_count() {
		return play_count;
	}

	public void setPlay_count(Long play_count) {
		this.play_count = play_count;
	}

	public Long getDigg_count() {
		return digg_count;
	}

	public void setDigg_count(Long digg_count) {
		this.digg_count = digg_count;
	}

	public Long getComment_count() {
		return comment_count;
	}

	public void setComment_count(Long comment_count) {
		this.comment_count = comment_count;
	}

	public String getHot_words() {
		return hot_words;
	}

	public void setHot_words(String hot_words) {
		this.hot_words = hot_words;
	}

	public Double getHot_value() {
		return hot_value;
	}

	public void setHot_value(Double hot_value) {
		this.hot_value = hot_value;
	}

	public String getItem_cover() {
		return item_cover;
	}

	public void setItem_cover(String item_cover) {
		this.item_cover = item_cover;
	}
}
