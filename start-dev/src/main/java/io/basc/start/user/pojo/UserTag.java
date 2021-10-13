package io.basc.start.user.pojo;

import java.io.Serializable;

import io.basc.framework.orm.annotation.PrimaryKey;
import io.swagger.v3.oas.annotations.media.Schema;

public class UserTag implements Serializable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Schema(description = "用户id")
	private long uid;
	@PrimaryKey
	@Schema(description = "标签")
	private String tag;
	@Schema(description = "标签描述")
	private String describe;
	@Schema(description = "创建时间")
	private long cts;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public long getCts() {
		return cts;
	}

	public void setCts(long cts) {
		this.cts = cts;
	}
}
