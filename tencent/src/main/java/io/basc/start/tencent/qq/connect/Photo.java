package io.basc.start.tencent.qq.connect;

import io.basc.framework.json.JsonObject;
import io.basc.framework.json.JsonObjectWrapper;

public class Photo extends JsonObjectWrapper {

	public Photo(JsonObject target) {
		super(target);
	}

	/**
	 * 照片的小图ID。照片采用双ID结构，根据任一ID都可获得照片信息
	 * 
	 * @return 小图ID
	 */
	public String getSloc() {
		return getAsString("sloc");
	}

	/**
	 * 照片的大图ID。照片采用双ID结构，根据任一ID都可获得照片信息
	 * 
	 * @return 大图ID
	 */
	public String getLloc() {
		return getAsString("lloc");
	}

	/**
	 * 照片的标题
	 * 
	 * @return 标题
	 */
	public String getName() {
		return getAsString("name");
	}

	/**
	 * 照片的描述信息
	 * 
	 * @return 描述信息
	 */
	public String getDesc() {
		return getAsString("desc");
	}

	/**
	 * 照片上次被修改的时间
	 * 
	 * @return 修改的时间
	 */
	public long getUpdatedTime() {
		return getAsLong("updated_time");
	}

	/**
	 * 照片的上传时间 2011-05-26 11:44:39
	 * 
	 * @return 上传时间
	 */
	public String getUploadedTime() {
		return getAsString("uploaded_time");
	}

	/**
	 * 照片的小图的url
	 * 
	 * @return 小图的url
	 */
	public String getSmallUrl() {
		return getAsString("small_url");
	}

	/**
	 * 照片的大图的详细信息集合
	 * 
	 * @return 大图的详细信息集合
	 */
	public LargeImage getLargeImage() {
		return getAsObject("large_image", LargeImage.class);
	}
}
