package io.basc.integration.bytedance.data;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class HotsearchSentences implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "热度 综合点赞、评论、转发等计算得出", example = "2.998e+06")
	private Long hot_level;
	@Schema(description = "热点词", example = "苹果发布AirPods Pro")
	private String sentence;

	public Long getHot_level() {
		return hot_level;
	}

	public void setHot_level(Long hot_level) {
		this.hot_level = hot_level;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}
}
