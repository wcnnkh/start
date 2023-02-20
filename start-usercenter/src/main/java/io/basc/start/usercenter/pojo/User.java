package io.basc.start.usercenter.pojo;

import io.basc.framework.orm.annotation.AutoIncrement;
import io.basc.framework.orm.annotation.PrimaryKey;
import io.basc.framework.orm.annotation.Unique;
import io.basc.framework.sql.orm.annotation.Table;
import io.basc.framework.util.StringUtils;
import io.basc.framework.util.TimeUtils;
import io.basc.start.template.utils.RegexUtils;
import io.basc.start.usercenter.model.UserAttributeModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Table
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class User extends UserAttributeModel {
	private static final long serialVersionUID = 1L;
	@AutoIncrement
	@PrimaryKey
	private long uid;
	private long cts;
	@Unique
	private String username;
	private String password;
	private long lastUpdatePasswordTime;
	@Unique
	private String phone;
	@Unique
	private String email;
	private int permissionGroupId;// 权限组id
	private boolean disable;// 是否禁用
	private long lastLoginTime;
	private long defaultAddressId;// 用户默认收货地址

	public String getCtsDescribe() {
		return TimeUtils.format(cts, "yyyy-MM-dd HH:mm:ss");
	}

	public String getLastLoginTimeDescribe() {
		return TimeUtils.format(lastLoginTime, "yyyy-MM-dd HH:mm:ss");
	}

	public String getAvailableNickname() {
		if (StringUtils.isNotEmpty(getNickname())) {
			return getNickname();
		}

		if (StringUtils.isNotEmpty(phone)) {
			return RegexUtils.hidePhone(phone);
		}

		if (StringUtils.isNotEmpty(username)) {
			return getUsername();
		}
		return null;
	}
}
