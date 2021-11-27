package io.basc.start.sms;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SendSmsRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	private MessageTemplate template;
	private Map<String, ?> templateParams;
	private String phone;
}
