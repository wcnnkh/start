package io.basc.start.vc.service;

import io.basc.framework.context.result.Result;
import io.basc.start.vc.enums.VerificationCodeType;

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
