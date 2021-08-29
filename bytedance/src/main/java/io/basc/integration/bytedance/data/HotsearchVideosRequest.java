package io.basc.integration.bytedance.data;

import io.basc.integration.bytedance.oauth.ClientRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class HotsearchVideosRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "热点词")
	private String hot_sentence;

	public String getHot_sentence() {
		return hot_sentence;
	}

	public void setHot_sentence(String hot_sentence) {
		this.hot_sentence = hot_sentence;
	}
}
