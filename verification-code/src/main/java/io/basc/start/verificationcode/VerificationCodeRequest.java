package io.basc.start.verificationcode;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VerificationCodeRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 接收人
	 */
	private VerificationCodeRecipient recipient;
	private String code;
}
