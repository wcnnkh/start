package scw.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternalItemPlay extends DataExternalMetadata {
	private static final long serialVersionUID = 1L;
	@Schema(description = "每日播放数", example = "200")
	private Long play;

	public Long getPlay() {
		return play;
	}

	public void setPlay(Long play) {
		this.play = play;
	}
}
