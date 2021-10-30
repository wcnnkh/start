package io.basc.start.app.user.pojo;

import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.framework.sql.orm.annotation.Table;

import java.io.Serializable;

@Table
public class UnionIdToUid implements Serializable{
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private String unionId;
	@PrimaryKey
	private int type;
	private long uid;
	
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
}
