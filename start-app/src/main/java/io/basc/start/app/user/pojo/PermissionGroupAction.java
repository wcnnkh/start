package io.basc.start.app.user.pojo;

import java.io.Serializable;

import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.framework.sql.orm.annotation.Table;
import lombok.Data;

@Table
@Data
public class PermissionGroupAction implements Serializable {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private int groupId;
	@PrimaryKey
	private String actionId;
}
