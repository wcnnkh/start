package io.basc.start.tencent.qq.connect;

import io.basc.framework.net.message.multipart.MultipartMessage;

public class UploadPicRequest extends QQRequest {
	private static final long serialVersionUID = 1L;
	private String photodesc;
	private String title;
	private String albumid;
	private Integer mobile;
	private String x;
	private String y;
	private final MultipartMessage picture;
	private Integer needfeed;
	private Integer successnum;
	private Integer picnum;

	public UploadPicRequest(String accessToken, String openid, MultipartMessage picture) {
		super(accessToken, openid);
		this.picture = picture;
	}

	public String getPhotodesc() {
		return photodesc;
	}

	public void setPhotodesc(String photodesc) {
		this.photodesc = photodesc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAlbumid() {
		return albumid;
	}

	public void setAlbumid(String albumid) {
		this.albumid = albumid;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

	public Integer getNeedfeed() {
		return needfeed;
	}

	public void setNeedfeed(Integer needfeed) {
		this.needfeed = needfeed;
	}

	public Integer getSuccessnum() {
		return successnum;
	}

	public void setSuccessnum(Integer successnum) {
		this.successnum = successnum;
	}

	public Integer getPicnum() {
		return picnum;
	}

	public void setPicnum(Integer picnum) {
		this.picnum = picnum;
	}

	public MultipartMessage getPicture() {
		return picture;
	}
}
