package scw.integration.tencent.qq.connect;

import java.util.Collections;
import java.util.List;

import scw.json.JsonArray;
import scw.json.JsonObject;

public class ListPhotoResponse extends QQResponse {

	public ListPhotoResponse(JsonObject target) {
		super(target);
	}

	public int getTotal() {
		return getIntValue("total");
	}

	public List<Photo> getPhotos() {
		JsonArray jsonArray = getJsonArray("photos");
		if(jsonArray == null) {
			return Collections.emptyList();
		}
		
		return jsonArray.convert(Photo.class);
	}
}
