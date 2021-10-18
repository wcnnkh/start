package io.basc.satrt.app.admin.editable.form;

import io.basc.start.data.annotation.Image;

public class ImageInput extends Input {
	private static final long serialVersionUID = 1L;
	private boolean multiple;
	private Integer width;
	private Integer height;

	public ImageInput() {
		super(InputType.IMAGE);
	}
	
	public ImageInput(Image image) {
		this();
		this.multiple = image.multiple();
		this.height = image.height();
		this.width = image.width();
	}

	public boolean isMultiple() {
		return multiple;
	}

	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
}
