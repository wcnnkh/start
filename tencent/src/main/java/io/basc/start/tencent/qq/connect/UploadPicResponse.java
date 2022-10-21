package io.basc.start.tencent.qq.connect;

import io.basc.framework.json.JsonObject;

public class UploadPicResponse extends QQResponse {

	public UploadPicResponse(JsonObject target) {
		super(target);
	}

	public String getAlbumid() {
		return getAsString("albumid");
	}

	public String getLloc() {
		return getAsString("lloc");
	}

	public String getSloc() {
		return getAsString("sloc");
	}

	public String getLargeUrl() {
		return getAsString("large_url");
	}

	public String getSmallUrl() {
		return getAsString("small_url");
	}

	public int getHeight() {
		return getAsInt("height");
	}

	public int getWidth() {
		return getAsInt("width");
	}
}
