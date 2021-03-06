package io.basc.start.tencent.wx.api.offiaccount.message.ordinary;


public class VideoOrdinaryMessage extends OrdinaryMessage{
	private static final long serialVersionUID = 1L;
	private String mediaId;//视频消息媒体id，可以调用获取临时素材接口拉取数据。
	private String thumbMediaId;//视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
}
