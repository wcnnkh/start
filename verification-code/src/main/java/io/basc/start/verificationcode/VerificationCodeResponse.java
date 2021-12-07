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
public class VerificationCodeResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private VerificationCodeRequest request;
	private boolean success;
	private String message;
}
