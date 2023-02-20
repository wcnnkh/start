package io.basc.start.usercenter.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AdminUserModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private String nickname;
	private boolean disable;
	private int groupId;
}
