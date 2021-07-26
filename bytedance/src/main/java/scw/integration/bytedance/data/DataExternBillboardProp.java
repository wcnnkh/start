package scw.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternBillboardProp extends Rank {
	private static final long serialVersionUID = 1L;
	@Schema(description = "排名变化", example = "1")
	private String rank_change;
	@Schema(description = "道具名", example = "测试名")
	private String name;
	@Schema(description = "展现量，离线数据（统计前一日数据）", example = "300")
	private Double show_cnt;
	@Schema(description = "开拍量，离线数据（统计前一日数据）", example = "300")
	private Double shoot_cnt;
	@Schema(description = "日投稿量，离线数据（统计前一日数据）", example = "300")
	private Double daily_issue_cnt;
	@Schema(description = "日投稿占比，格式:XX.XX% 精确小数点后2位 离线数据（统计前一日数据）", example = "30.61%")
	private String daily_issue_percent;
	@Schema(description = "日收藏数，离线数据（统计前一日数据）", example = "100")
	private Double daily_collection_cnt;
	@Schema(description = "日播放数，离线数据（统计前一日数据）", example = "100")
	private Double daily_play_cnt;
	@Schema(description = "影响力指数", example = "10000")
	private Double effect_value;

	public String getRank_change() {
		return rank_change;
	}

	public void setRank_change(String rank_change) {
		this.rank_change = rank_change;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getShow_cnt() {
		return show_cnt;
	}

	public void setShow_cnt(Double show_cnt) {
		this.show_cnt = show_cnt;
	}

	public Double getShoot_cnt() {
		return shoot_cnt;
	}

	public void setShoot_cnt(Double shoot_cnt) {
		this.shoot_cnt = shoot_cnt;
	}

	public Double getDaily_issue_cnt() {
		return daily_issue_cnt;
	}

	public void setDaily_issue_cnt(Double daily_issue_cnt) {
		this.daily_issue_cnt = daily_issue_cnt;
	}

	public String getDaily_issue_percent() {
		return daily_issue_percent;
	}

	public void setDaily_issue_percent(String daily_issue_percent) {
		this.daily_issue_percent = daily_issue_percent;
	}

	public Double getDaily_collection_cnt() {
		return daily_collection_cnt;
	}

	public void setDaily_collection_cnt(Double daily_collection_cnt) {
		this.daily_collection_cnt = daily_collection_cnt;
	}

	public Double getDaily_play_cnt() {
		return daily_play_cnt;
	}

	public void setDaily_play_cnt(Double daily_play_cnt) {
		this.daily_play_cnt = daily_play_cnt;
	}

	public Double getEffect_value() {
		return effect_value;
	}

	public void setEffect_value(Double effect_value) {
		this.effect_value = effect_value;
	}
}
