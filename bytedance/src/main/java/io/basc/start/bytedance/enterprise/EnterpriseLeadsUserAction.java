package io.basc.start.bytedance.enterprise;

import java.io.Serializable;

public class EnterpriseLeadsUserAction implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long action_flag;
	private String action_source;
	private Long action_type;
	private Long create_time;
	private String user_id;

	public Long getAction_flag() {
		return action_flag;
	}

	public void setAction_flag(Long action_flag) {
		this.action_flag = action_flag;
	}

	public String getAction_source() {
		return action_source;
	}

	public void setAction_source(String action_source) {
		this.action_source = action_source;
	}

	public Long getAction_type() {
		return action_type;
	}

	public void setAction_type(Long action_type) {
		this.action_type = action_type;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
}
