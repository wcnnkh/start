package scw.integration.bytedance.enterprise;

import scw.integration.bytedance.user.UserPagingRequest;

public class EnterpriseLeadsUserActionListRequest extends UserPagingRequest {
	private static final long serialVersionUID = 1L;
	private String user_id;
	private String action_type;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getAction_type() {
		return action_type;
	}

	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
}
