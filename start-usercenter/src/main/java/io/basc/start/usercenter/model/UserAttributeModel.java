package io.basc.start.usercenter.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;

import io.basc.framework.util.StringUtils;
import io.basc.framework.util.TimeUtils;
import io.basc.start.usercenter.enums.SexType;
import lombok.Data;

@Data
public class UserAttributeModel implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date birthday;
	private SexType sex;
	private int age;
	// 创建age时候用的时间
	private long createAgeTime;

	private String nickname;
	private String headImg;

	public int getActualAge() {
		if (birthday != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(birthday);
			return TimeUtils.DAY.getMinCalendar().get(Calendar.YEAR) - calendar.get(Calendar.YEAR);
		} else {
			if (createAgeTime <= 0) {
				return age;
			}

			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(createAgeTime);
			return TimeUtils.DAY.getMinCalendar().get(Calendar.YEAR) - calendar.get(Calendar.YEAR) + age;
		}
	}

	public void writeTo(UserAttributeModel userAttributeModel) {
		if (birthday != null) {
			userAttributeModel.setBirthday(birthday);
		}

		if (sex != null) {
			userAttributeModel.setSex(sex);
		}

		if (age != 0 && age != userAttributeModel.getAge()) {
			userAttributeModel.setAge(age);
		}

		if (StringUtils.isNotEmpty(nickname)) {
			userAttributeModel.setNickname(nickname);
		}

		if (StringUtils.isNotEmpty(headImg)) {
			userAttributeModel.setHeadImg(headImg);
		}
	}
}
