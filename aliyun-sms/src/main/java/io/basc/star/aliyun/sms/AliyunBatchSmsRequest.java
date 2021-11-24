package io.basc.star.aliyun.sms;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

public class AliyunBatchSmsRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "接收人", required = true)
	@NotEmpty
	private List<AliyunSmsReceiver> receivers;
	@Schema(description = "短信模板CODE。", required = true, example = "SMS_15255****")
	@NotEmpty
	private String templateCode;

	public List<AliyunSmsReceiver> getReceivers() {
		return receivers;
	}

	public void setReceivers(List<AliyunSmsReceiver> receivers) {
		this.receivers = receivers;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
}
