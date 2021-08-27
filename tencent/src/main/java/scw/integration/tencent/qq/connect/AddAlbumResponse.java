package scw.integration.tencent.qq.connect;

import io.basc.framework.json.JsonObject;

public class AddAlbumResponse extends QQResponse{

	public AddAlbumResponse(JsonObject target) {
		super(target);
	}
	
	public AlbumInfo getAlbumInfo(){
		return new AlbumInfo(getJsonObject("album"));
	}
}
