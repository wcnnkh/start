package scw.integration.bytedance.video;

import io.swagger.v3.oas.annotations.media.Schema;

public class VdeoPartUploadRequest extends VideoPartRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "表示该分片在整个视频内的相对位置，从1开始（1即表示第一段视频分片）。", example = "1")
	private Long part_number;

	public Long getPart_number() {
		return part_number;
	}

	public void setPart_number(Long part_number) {
		this.part_number = part_number;
	}
}
