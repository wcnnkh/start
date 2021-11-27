package io.basc.start.sms;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendSmsResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	private SendSmsRequest request;
	private boolean success;
	private String message;
}
