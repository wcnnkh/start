package io.basc.start.verificationcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import io.basc.framework.util.CollectionUtils;

public interface VerificationCodeService extends VerificationCodeSender {
	default VerificationCodeResponse random(VerificationCodeRecipient recipient) {
		return random(Arrays.asList(recipient)).get(0);
	}

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

	String[] getRandomCodes(int size);

	default VerificationCodeResponse validate(VerificationCodeRequest request) {
		return validate(Arrays.asList(request)).get(0);
	}

	List<VerificationCodeResponse> validate(List<VerificationCodeRequest> requests);
}
