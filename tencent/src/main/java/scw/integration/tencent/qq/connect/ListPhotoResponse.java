package scw.integration.tencent.qq.connect;

import io.basc.framework.json.JsonArray;
import io.basc.framework.json.JsonObject;

import java.util.Collections;
import java.util.List;

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
