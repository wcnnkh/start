package scw.integration.bytedance.video;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class VideoData implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "表示视频状态。1:已发布;2:不适宜公开;4:审核中", example = "1")
	private Integer video_status;
	@Schema(description = "视频封面", example = "https://p3-dy.byteimg.com/img/tos-cn-p-0015/cfa0d6421bdc4580876cb16974539ff6~c5_300x400.jpeg")
	private String cover;
	@Schema(description = "视频创建时间戳")
	private Long create_time;
	@Schema(description = "是否置顶")
	private Boolean is_top;
	@Schema(description = "视频id")
	private String item_id;
	@Schema(description = "表示是否审核结束。审核通过或者失败都会返回true，审核中返回false。", example = "true")
	private Boolean is_reviewed;
	@Schema(description = "视频播放页面。视频播放页可能会失效，请在观看视频前调用/video/data/获取最新的播放页", example = "https://www.iesdouyin.com/share/video/QDlWd0EzdWVMU2Q0aU5tKzVaOElvVU03ODBtRHFQUCtLUHBSMHFRT21MVkFYYi9UMDYwemRSbVlxaWczNTd6RUJRc3MrM2hvRGlqK2EwNnhBc1lGUkpRPT0=/?region=CN&mid=6753173704399670023&u_code=12h9je425&titleType=title")
	private String share_url;
	@Schema(description = "统计数据")
	private VideoStatistics statistics;
	@Schema(description = "视频标题", example = "测试视频 #测试话题 @抖音小助手")
	private String title;

	public Integer getVideo_status() {
		return video_status;
	}

	public void setVideo_status(Integer video_status) {
		this.video_status = video_status;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public Boolean getIs_top() {
		return is_top;
	}

	public void setIs_top(Boolean is_top) {
		this.is_top = is_top;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public Boolean getIs_reviewed() {
		return is_reviewed;
	}

	public void setIs_reviewed(Boolean is_reviewed) {
		this.is_reviewed = is_reviewed;
	}

	public String getShare_url() {
		return share_url;
	}

	public void setShare_url(String share_url) {
		this.share_url = share_url;
	}

	public VideoStatistics getStatistics() {
		return statistics;
	}

	public void setStatistics(VideoStatistics statistics) {
		this.statistics = statistics;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
