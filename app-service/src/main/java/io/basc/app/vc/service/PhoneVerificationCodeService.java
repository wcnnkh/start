package io.basc.app.vc.service;

import io.basc.app.vc.enums.VerificationCodeType;
import io.basc.framework.context.result.Result;

/**
 * 手机号验证码服务
 * 
 * @author shuchaowen
 *
 */
public interface PhoneVerificationCodeService {
	/**
	 * 发送验证码
	 * 
	 * @param phone
	 * @param type
	 * @return
	 */
	Result send(String phone, VerificationCodeType type);

	/**
	 * 检查验证码
	 * 
	 * @param phone
	 * @param code
	 * @param type
	 * @return
	 */
	Result check(String phone, String code, VerificationCodeType type);
}
