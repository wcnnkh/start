package io.basc.integration.tencent.qq.connect;

import io.basc.framework.json.JsonObject;

import java.util.List;

public class ListAlbumResponse extends QQResponse {

	public ListAlbumResponse(JsonObject target) {
		super(target);
	}

	/**
	 * albumnum
	 * 
	 * @return
	 */
	public int getAlbumnum() {
		return getIntValue("albumnum");
	}

	public List<AlbumInfo> getAlbums() {
		return AlbumInfo.parse(getJsonArray("album"));
	}
}
