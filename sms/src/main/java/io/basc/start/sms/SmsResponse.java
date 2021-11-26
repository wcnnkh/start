package io.basc.start.sms;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private SmsRequest request;
	private boolean success;
	private String message;
}
