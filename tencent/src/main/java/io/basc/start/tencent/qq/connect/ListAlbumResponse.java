package io.basc.start.tencent.qq.connect;

import io.basc.framework.json.JsonObject;

import java.util.List;

public class ListAlbumResponse extends QQResponse {

	public ListAlbumResponse(JsonObject target) {
		super(target);
	}

	public int getAlbumnum() {
		return getAsInt("albumnum");
	}

	public List<AlbumInfo> getAlbums() {
		return AlbumInfo.parse(getJsonArray("album"));
	}
}
