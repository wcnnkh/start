package io.basc.start.sms;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Valid
public class MessageTemplate implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "短信模版签名", required = true)
	@NotEmpty
	private String signName;
	@Schema(description = "签名模版编码", required = true)
	@NotEmpty
	private String code;
}
