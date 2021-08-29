package io.basc.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalSdkShare extends DataExternalMetadata {
	private static final long serialVersionUID = 1L;
	@Schema(description = "累计分享到抖音内且状态已被删除的视频总数", example = "200")
	private Long delete_item_total;
	@Schema(description = "当日分享到抖音的视频数", example = "200")
	private Long share_item_count;
	@Schema(description = "累计分享到抖音的视频总数", example = "200")
	private Long share_item_total;
	@Schema(description = "当日完成分享的用户数", example = "200")
	private Long share_user_count;

	public Long getDelete_item_total() {
		return delete_item_total;
	}

	public void setDelete_item_total(Long delete_item_total) {
		this.delete_item_total = delete_item_total;
	}

	public Long getShare_item_count() {
		return share_item_count;
	}

	public void setShare_item_count(Long share_item_count) {
		this.share_item_count = share_item_count;
	}

	public Long getShare_item_total() {
		return share_item_total;
	}

	public void setShare_item_total(Long share_item_total) {
		this.share_item_total = share_item_total;
	}

	public Long getShare_user_count() {
		return share_user_count;
	}

	public void setShare_user_count(Long share_user_count) {
		this.share_user_count = share_user_count;
	}
}
