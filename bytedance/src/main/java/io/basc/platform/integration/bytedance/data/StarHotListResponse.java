package io.basc.platform.integration.bytedance.data;

import io.basc.platform.integration.bytedance.ResponseCodeList;
import io.swagger.v3.oas.annotations.media.Schema;

public class StarHotListResponse extends ResponseCodeList<StartHot> {
	private static final long serialVersionUID = 1L;
	@Schema(description = "达人热榜类型", example = "1")
	private Long hot_list_type;
	@Schema(description = "达人热榜更新时间戳", example = "1.584418477e+09")
	private Long hot_list_update_timestamp;
	@Schema(description = "热榜类型说明", example = "【筛选规则】：综合评估创作者近期作品的有效视频数据、性价比、信用分、有效涨粉、种草指数等加权取值进行排序，以内容数据为主要衡量标准。【达人优势】：综合能力高，具有较大的商业价值。【适用场景】：适用于大部分营销场景，可作为各领域商业投放的必选名单。")
	private String hot_list_description;

	public Long getHot_list_type() {
		return hot_list_type;
	}

	public void setHot_list_type(Long hot_list_type) {
		this.hot_list_type = hot_list_type;
	}

	public Long getHot_list_update_timestamp() {
		return hot_list_update_timestamp;
	}

	public void setHot_list_update_timestamp(Long hot_list_update_timestamp) {
		this.hot_list_update_timestamp = hot_list_update_timestamp;
	}

	public String getHot_list_description() {
		return hot_list_description;
	}

	public void setHot_list_description(String hot_list_description) {
		this.hot_list_description = hot_list_description;
	}
}
