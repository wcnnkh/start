package io.basc.star.aliyun.sms;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

/**
 * 短信模版
 * 
 * @author shuchaowen
 *
 */
public class MessageModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "短信模版签名", required = true)
	@NotEmpty
	private String sms_free_sign_name;
	@Schema(description = "签名模版编码", required = true)
	@NotEmpty
	private String sms_template_code;

	/**
	 * 用于序列化
	 */
	protected MessageModel() {
	}

	public MessageModel(String sms_free_sign_name, String sms_template_code) {
		this.sms_free_sign_name = sms_free_sign_name;
		this.sms_template_code = sms_template_code;
	}

	public final String getSms_free_sign_name() {
		return sms_free_sign_name;
	}

	public final String getSms_template_code() {
		return sms_template_code;
	}
}
