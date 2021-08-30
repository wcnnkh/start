package io.basc.start.bytedance.user;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class FansCheckRequest extends UserRequest {
	private static final long serialVersionUID = 1L;
	@Schema(example = "ba253642-0590-40bc-9bdf-9a1334b94059", required = true)
	@NotNull
	private String follower_open_id;

	public String getFollower_open_id() {
		return follower_open_id;
	}

	public void setFollower_open_id(String follower_open_id) {
		this.follower_open_id = follower_open_id;
	}
}
