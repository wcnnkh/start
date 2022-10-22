package io.basc.start.tencent.qq.connect;

import io.basc.framework.json.JsonArray;
import io.basc.framework.json.JsonObject;
import io.basc.framework.json.JsonObjectWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AlbumInfo extends JsonObjectWrapper {

	public AlbumInfo(JsonObject target) {
		super(target);
	}

	/**
	 * 相册ID
	 * 
	 * @return
	 */
	public String getAlbumId() {
		return getAsString("albumid");
	}

	/**
	 * 相册分类ID
	 * 
	 * @return
	 */
	public int getClassId() {
		return getAsInt("classid");
	}

	/**
	 * 相册创建时间
	 * 
	 * @return
	 */
	public long getCreatetime() {
		return getAsLong("createtime");
	}

	/**
	 * 相册描述
	 * 
	 * @return
	 */
	public String getDesc() {
		return getAsString("desc");
	}

	/**
	 * 相册名称
	 * 
	 * @return
	 */
	public String getName() {
		return getAsString("name");
	}

	/**
	 * 相册封面照片地址
	 * 
	 * @return
	 */
	public String getCoverUrl() {
		return getAsString("coverurl");
	}

	/**
	 * 照片数
	 * 
	 * @return
	 */
	public int getPicnum() {
		return getAsInt("picnum");
	}

	/**
	 * 相册权限
	 * 
	 * @return
	 */
	public int getPriv() {
		return getAsInt("priv");
	}

	public static List<AlbumInfo> parse(JsonArray jsonArray) {
		if (jsonArray == null) {
			return null;
		}

		if (jsonArray.isEmpty()) {
			return Collections.emptyList();
		}

		List<AlbumInfo> list = new ArrayList<AlbumInfo>();
		for (int i = 0, len = jsonArray.size(); i < len; i++) {
			list.add(new AlbumInfo(jsonArray.getJsonObject(i)));
		}
		return list;
	}
}
