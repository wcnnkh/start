package io.basc.platform.integration.bytedance.data;

import io.basc.platform.integration.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class StarAuthorScore extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "性价比指数", example = "80.6")
	private Double cp_score;
	@Schema(description = "种草指数", example = "80.6")
	private Double shop_score;
	@Schema(description = "传播指数", example = "80.6")
	private Double spread_score;
	@Schema(description = "星图指数", example = "80.6")
	private Double star_score;
	@Schema(description = "合作指数", example = "80.6")
	private Double cooperation_score;
	@Schema(description = "粉丝数")
	private Long follower;
	@Schema(description = "涨粉指数", example = "80.6")
	private Double growth_score;
	@Schema(description = "达人昵称")
	private String nick_name;
	@Schema(defaultValue = "达人指数更新时间戳")
	private Long update_timestamp;

	public Double getCp_score() {
		return cp_score;
	}

	public void setCp_score(Double cp_score) {
		this.cp_score = cp_score;
	}

	public Double getShop_score() {
		return shop_score;
	}

	public void setShop_score(Double shop_score) {
		this.shop_score = shop_score;
	}

	public Double getSpread_score() {
		return spread_score;
	}

	public void setSpread_score(Double spread_score) {
		this.spread_score = spread_score;
	}

	public Double getStar_score() {
		return star_score;
	}

	public void setStar_score(Double star_score) {
		this.star_score = star_score;
	}

	public Double getCooperation_score() {
		return cooperation_score;
	}

	public void setCooperation_score(Double cooperation_score) {
		this.cooperation_score = cooperation_score;
	}

	public Long getFollower() {
		return follower;
	}

	public void setFollower(Long follower) {
		this.follower = follower;
	}

	public Double getGrowth_score() {
		return growth_score;
	}

	public void setGrowth_score(Double growth_score) {
		this.growth_score = growth_score;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public Long getUpdate_timestamp() {
		return update_timestamp;
	}

	public void setUpdate_timestamp(Long update_timestamp) {
		this.update_timestamp = update_timestamp;
	}
}
