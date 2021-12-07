package io.basc.star.aliyun.sms;

import io.basc.start.sms.SendSmsRequest;
import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import javax.validation.Valid;
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
public class AliyunSendSmsRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	@Valid
	private SendSmsRequest send;
	@Schema(description = "上行短信扩展码，上行短信，指发送给通信服务提供商的短信，用于定制某种服务、完成查询，或是办理某种业务等，需要收费的，按运营商普通短信资费进行扣费。", example = "90999")
	private String upExtendCode;
	private String qutId;
}
