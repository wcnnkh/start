package io.basc.start.bytedance.video;

import io.basc.start.bytedance.oauth.ClientRequest;
import io.swagger.v3.oas.annotations.media.Schema;

public class ShareIdRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "如果需要知道视频分享成功的结果，need_callback设置为true")
	private Boolean need_callback;
	@Schema(description = "多来源样式id（暂未开放）")
	private String source_style_id;
	@Schema(description = "追踪分享默认hashtag")
	private String default_hashtag;
	@Schema(description = "分享来源url附加参数（暂未开放）")
	private String link_param;

	public Boolean getNeed_callback() {
		return need_callback;
	}

	public void setNeed_callback(Boolean need_callback) {
		this.need_callback = need_callback;
	}

	public String getSource_style_id() {
		return source_style_id;
	}

	public void setSource_style_id(String source_style_id) {
		this.source_style_id = source_style_id;
	}

	public String getDefault_hashtag() {
		return default_hashtag;
	}

	public void setDefault_hashtag(String default_hashtag) {
		this.default_hashtag = default_hashtag;
	}

	public String getLink_param() {
		return link_param;
	}

	public void setLink_param(String link_param) {
		this.link_param = link_param;
	}
}
