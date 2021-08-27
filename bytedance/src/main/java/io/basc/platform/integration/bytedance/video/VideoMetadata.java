package io.basc.platform.integration.bytedance.video;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class VideoMetadata implements Serializable {
	private static final long serialVersionUID = 1L;
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
}
