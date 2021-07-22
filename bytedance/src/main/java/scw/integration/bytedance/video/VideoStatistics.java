package scw.integration.bytedance.video;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class VideoStatistics implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "点赞数", example = "200")
	private Integer digg_count;
	@Schema(description = "下载数", example = "10")
	private Integer download_count;
	@Schema(description = "转发数", example = "10")
	private Integer forward_count;
	@Schema(description = "播放数，只有作者本人可见。公开视频设为私密后，播放数也会返回0。", example = "300")
	private Integer play_count;
	@Schema(description = "分享数", example = "10")
	private Integer share_count;
	@Schema(description = "评论数", example = "100")
	private Integer comment_count;

	public Integer getDigg_count() {
		return digg_count;
	}

	public void setDigg_count(Integer digg_count) {
		this.digg_count = digg_count;
	}

	public Integer getDownload_count() {
		return download_count;
	}

	public void setDownload_count(Integer download_count) {
		this.download_count = download_count;
	}

	public Integer getForward_count() {
		return forward_count;
	}

	public void setForward_count(Integer forward_count) {
		this.forward_count = forward_count;
	}

	public Integer getPlay_count() {
		return play_count;
	}

	public void setPlay_count(Integer play_count) {
		this.play_count = play_count;
	}

	public Integer getShare_count() {
		return share_count;
	}

	public void setShare_count(Integer share_count) {
		this.share_count = share_count;
	}

	public Integer getComment_count() {
		return comment_count;
	}

	public void setComment_count(Integer comment_count) {
		this.comment_count = comment_count;
	}
}
