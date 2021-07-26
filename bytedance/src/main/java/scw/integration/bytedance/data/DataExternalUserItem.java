package scw.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalUserItem extends DataExternalMetadata {
	private static final long serialVersionUID = 1L;
	@Schema(description = "每日发布内容数", example = "200")
	private Long new_issue;
	@Schema(description = "每天新增视频播放", example = "200")
	private Long new_play;
	@Schema(description = "每日内容总数", example = "200")
	private Long total_issue;

	public Long getNew_issue() {
		return new_issue;
	}

	public void setNew_issue(Long new_issue) {
		this.new_issue = new_issue;
	}

	public Long getNew_play() {
		return new_play;
	}

	public void setNew_play(Long new_play) {
		this.new_play = new_play;
	}

	public Long getTotal_issue() {
		return total_issue;
	}

	public void setTotal_issue(Long total_issue) {
		this.total_issue = total_issue;
	}
}
