package io.basc.platform.integration.bytedance.comment;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class ImMessageContent implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "文字内容")
	private String text;
	@Schema(description = "素材id")
	private String media_id;
	@Schema(description = "视频id")
	private String item_id;
	@Schema(description = "卡片id")
	private String card_id;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
}
