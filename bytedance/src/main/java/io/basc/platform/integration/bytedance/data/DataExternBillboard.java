package io.basc.platform.integration.bytedance.data;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternBillboard extends Rank {
	private static final long serialVersionUID = 1L;
	@Schema(description = "排名变化, 如果上一期未上榜用-表示", example = "1")
	private String rank_change;
	@Schema(description = "昵称", example = "昵称")
	private String nickname;
	@Schema(description = "头像", example = "https://example.com/x.jpeg")
	private String avatar;
	@Schema(description = "粉丝数", example = "200")
	private Long follower_count;
	@Schema(description = "近一月在榜次数", example = "200")
	private Integer onbillbaord_times;
	@Schema(description = "影响力指数", example = "10000")
	private Double effect_value;
	@Schema(description = "视频列表")
	private List<Video> video_list;

	public static class Video {
		@Schema(description = "视频播放页面。视频播放页可能会失效，请在观看视频前调用/video/data/获取最新的播放页。", example = "https://www.iesdouyin.com/share/video/QDlWd0EzdWVMU2Q0aU5tKzVaOElvVU03ODBtRHFQUCtLUHBSMHFRT21MVkFYYi9UMDYwemRSbVlxaWczNTd6RUJRc3MrM2hvRGlqK2EwNnhBc1lGUkpRPT0=/?region=CN&mid=6753173704399670023&u_code=12h9je425&titleType=title")
		private String share_url;
		@Schema(description = "视频标题", example = "测试视频 #测试话题 @抖音小助手")
		private String title;
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

		public String getItem_cover() {
			return item_cover;
		}

		public void setItem_cover(String item_cover) {
			this.item_cover = item_cover;
		}
	}
}
