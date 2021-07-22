package scw.integration.bytedance.video;

import scw.integration.bytedance.ResponseCode;

public class ImageUploadResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	private ImageInfo image;

	public ImageInfo getImage() {
		return image;
	}

	public void setImage(ImageInfo image) {
		this.image = image;
	}
}
