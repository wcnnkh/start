package io.basc.platform.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternBillboardMusic extends Rank {
	private static final long serialVersionUID = 1L;
	@Schema(description = "音乐封面", example = "https://example.com/x.jpeg")
	private String cover;
	@Schema(description = "歌曲标题", example = "测试标题")
	private String title;
	@Schema(description = "时长，精确到秒", example = "10")
	private Integer duration;
	@Schema(description = "作者昵称", example = "昵称")
	private String author;
	@Schema(description = "使用量", example = "10000")
	private Long use_count;
	@Schema(description = "音乐分享链接", example = "https://example.com/xxx")
	private String share_url;

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

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getUse_count() {
		return use_count;
	}

	public void setUse_count(Long use_count) {
		this.use_count = use_count;
	}

	public String getShare_url() {
		return share_url;
	}

	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}
}
