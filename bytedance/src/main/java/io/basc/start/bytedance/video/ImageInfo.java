package io.basc.start.bytedance.video;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class ImageInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(example = "360")
	private Long height;
	@Schema(description = "图片id")
	private String image_id;
	@Schema(example = "360")
	private Long width;

	public Long getHeight() {
		return height;
	}

	public void setHeight(Long height) {
		this.height = height;
	}

	public String getImage_id() {
		return image_id;
	}

	public void setImage_id(String image_id) {
		this.image_id = image_id;
	}

	public Long getWidth() {
		return width;
	}

	public void setWidth(Long width) {
		this.width = width;
	}
}
