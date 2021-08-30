package io.basc.start.bytedance.enterprise;

import java.util.List;

import io.basc.start.bytedance.PagingResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class EnterpriseLeadsUserListResponse extends PagingResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "用户OPENID (见users)")
	private List<String> list;
	private List<EnterpriseUser> users;

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public List<EnterpriseUser> getUsers() {
		return users;
	}

	public void setUsers(List<EnterpriseUser> users) {
		this.users = users;
	}
}
