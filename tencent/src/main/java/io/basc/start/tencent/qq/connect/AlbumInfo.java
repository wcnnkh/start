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
	 * @return 相册ID
	 */
	public String getAlbumId() {
		return getAsString("albumid");
	}

	/**
	 * 相册分类ID
	 * 
	 * @return 相册分类ID
	 */
	public int getClassId() {
		return getAsInt("classid");
	}

	/**
	 * 相册创建时间
	 * 
	 * @return 相册创建时间
	 */
	public long getCreatetime() {
		return getAsLong("createtime");
	}

	/**
	 * 相册描述
	 * 
	 * @return 相册描述
	 */
	public String getDesc() {
		return getAsString("desc");
	}

	/**
	 * 相册名称
	 * 
	 * @return 相册名称
	 */
	public String getName() {
		return getAsString("name");
	}

	/**
	 * 相册封面照片地址
	 * 
	 * @return 照片地址
	 */
	public String getCoverUrl() {
		return getAsString("coverurl");
	}

	/**
	 * 照片数
	 * 
	 * @return 照片数
	 */
	public int getPicnum() {
		return getAsInt("picnum");
	}

	/**
	 * 相册权限
	 * 
	 * @return 相册权限
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
