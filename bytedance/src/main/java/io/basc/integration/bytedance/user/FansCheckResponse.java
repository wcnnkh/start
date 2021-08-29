package io.basc.integration.bytedance.user;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

public class FansCheckResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "	follower_open_id是否关注了open_id", example = "true")
	private Boolean is_follower;
	@Schema(description = "若关注了，则返回关注时间。单位为秒级时间戳", example = "1571075129")
	private Long follow_time;

	public Boolean getIs_follower() {
		return is_follower;
	}

	public void setIs_follower(Boolean is_follower) {
		this.is_follower = is_follower;
	}

	public Long getFollow_time() {
		return follow_time;
	}

	public void setFollow_time(Long follow_time) {
		this.follow_time = follow_time;
	}
}
