package io.basc.start.app.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserAndPassword implements Serializable {
	private static final long serialVersionUID = 1L;
	private String user;
	private String password;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
