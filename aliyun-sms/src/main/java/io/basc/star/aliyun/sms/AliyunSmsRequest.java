package io.basc.star.aliyun.sms;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

public class AliyunSmsRequest extends AliyunSmsReceiver {
	private static final long serialVersionUID = 1L;
	@Schema(description = "短信模板CODE。", required = true, example = "SMS_15255****")
	@NotEmpty
	private String templateCode;
	@Schema(description = "外部流水扩展字段。", example = "abcdefgh")
	private String qutId;
}
