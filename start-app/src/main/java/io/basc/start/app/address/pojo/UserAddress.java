package io.basc.start.app.address.pojo;

import io.basc.framework.orm.annotation.AutoIncrement;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.framework.sql.orm.annotation.Index;
import io.basc.framework.sql.orm.annotation.Table;
import io.basc.start.app.address.model.UserAddressModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Table
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserAddress extends UserAddressModel {
	private static final long serialVersionUID = 1L;
	@AutoIncrement
	@PrimaryKey
	private long id;
	@Index
	private long uid;
	private long createTime;
	private long lastUpdateTime;
}
