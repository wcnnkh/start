package io.basc.start.sms;

import java.io.Serializable;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class SendSmsRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	@Valid
	private MessageTemplate template;
	private Map<String, ?> templateParams;
	@NotEmpty
	private String phone;
}
