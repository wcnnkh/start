package io.basc.start.verificationcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import io.basc.framework.util.CollectionUtils;

public interface VerificationCodeService extends VerificationCodeSender {
	/**
	 * 发送一个随机的验证码
	 * 
	 * @param recipient
	 * @return
	 */
	default VerificationCodeResponse random(VerificationCodeRecipient recipient) {
		return random(Arrays.asList(recipient)).get(0);
	}

	/**
	 * 批量发送随机验证码
	 * 
	 * @param recipients
	 * @return 返回和请求的顺序一致
	 */
	default List<VerificationCodeResponse> random(List<VerificationCodeRecipient> recipients) {
		if (CollectionUtils.isEmpty(recipients)) {
			return Collections.emptyList();
		}

		String[] randomCodes = getRandomCodes(recipients.size());
		int i = 0;
		Iterator<? extends VerificationCodeRecipient> recipientIterator = recipients.iterator();
		List<VerificationCodeRequest> requests = new ArrayList<>(recipients.size());
		while (recipientIterator.hasNext()) {
			requests.add(VerificationCodeRequest.builder().recipient(recipientIterator.next()).code(randomCodes[i++])
					.build());
		}
		return send(requests);
	}

	/**
	 * 获取指定数量的随机验证码
	 * 
	 * @param size
	 * @return
	 */
	String[] getRandomCodes(int size);

	/**
	 * 验证验证码
	 * 
	 * @param request
	 * @return
	 */
	default VerificationCodeResponse validate(VerificationCodeRequest request) {
		return validate(Arrays.asList(request)).get(0);
	}

	/**
	 * 批量验证
	 * 
	 * @param requests
	 * @return
	 */
	List<VerificationCodeResponse> validate(List<VerificationCodeRequest> requests);
}
