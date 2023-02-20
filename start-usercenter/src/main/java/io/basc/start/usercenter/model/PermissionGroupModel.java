package io.basc.start.usercenter.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class PermissionGroupModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private int parentId;
	private String name;
	private boolean disable;// 是否禁用
}
