package io.basc.integration.bytedance.video;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class ImageCreateRequest extends CreateRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "通过/image/upload/接口得到。", example = "v0201f510000smhdsr0ggl1v4a2b2ps1", required = true)
	@NotNull
	private String image_id;

	public String getImage_id() {
		return image_id;
	}

	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}
}
