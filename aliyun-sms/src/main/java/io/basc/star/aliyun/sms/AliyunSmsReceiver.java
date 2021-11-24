package io.basc.star.aliyun.sms;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

public class AliyunSmsReceiver implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "接收短信的手机号码。\r\n" + "\r\n" + "手机号码格式：\r\n" + "\r\n"
			+ "国内短信：+/+86/0086/86或无任何前缀的11位手机号码，例如1590000****。\r\n"
			+ "国际/港澳台消息：国际区号+号码，例如852000012****。", required = true, example = "1590000****")
	@NotEmpty
	private String phone;
	@Schema(description = "短信模版签名", required = true, example = "阿里云")
	@NotEmpty
	private String signName;
	@Schema(description = "短信模板变量对应的实际值", example = "{\\\"name\\\":\\\"TemplateParamJson\\\"}")
	private Map<String, ?> templateParams;
	@Schema(description = "上行短信扩展码，上行短信，指发送给通信服务提供商的短信，用于定制某种服务、完成查询，或是办理某种业务等，需要收费的，按运营商普通短信资费进行扣费。", example = "90999")
	private String smsUpExtendCode;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public Map<String, ?> getTemplateParams() {
		return templateParams;
	}

	public void setTemplateParams(Map<String, ?> templateParams) {
		this.templateParams = templateParams;
	}
}
