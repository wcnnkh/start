package io.basc.satrt.app.admin.editable.form;

import io.basc.framework.util.Assert;
import io.basc.start.editable.ImageAttributes;

public class ImageInput extends Input {
	private static final long serialVersionUID = 1L;
	private boolean multiple;
	private Integer width;
	private Integer height;

	public ImageInput() {
		super(InputType.IMAGE);
	}

	public ImageInput(ImageAttributes image) {
		this();
		Assert.requiredArgument(image != null, "image");
		this.multiple = image.isMultiple();
		this.height = image.getHeight();
		this.width = image.getWidth();
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
