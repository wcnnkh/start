package io.basc.start.bytedance.data;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class StartHot implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "粉丝数")
	private Long follower;
	@Schema(description = "达人昵称")
	private String nick_name;
	@Schema(description = "	热榜排名")
	private Long rank;
	@Schema(description = "热榜类型对应的热榜指数", example = "80.6")
	private Double score;
	private String[] tags;
	@Schema(description = "抖音号")
	private String unique_id;

	public Long getFollower() {
		return follower;
	}

	public void setFollower(Long follower) {
		this.follower = follower;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public Long getRank() {
		return rank;
	}

	public void setRank(Long rank) {
		this.rank = rank;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getUnique_id() {
		return unique_id;
	}

	public void setUnique_id(String unique_id) {
		this.unique_id = unique_id;
	}
}
