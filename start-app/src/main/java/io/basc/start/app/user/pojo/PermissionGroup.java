package io.basc.start.app.user.pojo;

import io.basc.framework.orm.annotation.AutoIncrement;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.framework.sql.orm.annotation.Table;
import io.basc.start.app.user.model.PermissionGroupModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Table
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PermissionGroup extends PermissionGroupModel {
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@AutoIncrement
	private int id;
}
