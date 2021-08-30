package io.basc.start.bytedance.poi;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class Recommend implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "推荐内容链接(图片，或者视频链接）", example = "https://static.dingdandao.com/0ffaf1b5a669ea216a960c97ded9a3b0")
	private String image_url;
	@Schema(description = "推荐描述", example = "石锅浓汤海鲜豆腐窝")
	private String title;

	public String getImage_url() {
		return image_url;
	}

	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
