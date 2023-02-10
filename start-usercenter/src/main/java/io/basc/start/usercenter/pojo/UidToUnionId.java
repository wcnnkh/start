package io.basc.start.usercenter.pojo;

import java.io.Serializable;

import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.framework.sql.orm.annotation.Table;

@Table
public class UidToUnionId implements Serializable{
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private long uid;
	@PrimaryKey
	private int type;
	private String unionId;
	
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getUnionId() {
		return unionId;
	}
	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}
}
