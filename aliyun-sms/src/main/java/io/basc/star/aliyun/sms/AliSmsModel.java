package io.basc.star.aliyun.sms;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import io.basc.framework.mapper.MapperUtils;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 短信模版
 * 
 * @author shuchaowen
 *
 */
public class AliSmsModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "短信模版签名", required = true)
	@NotEmpty
	private String signName;
	@Schema(description = "签名模版编码", required = true)
	@NotEmpty
	private String templateCode;

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	@Override
	public String toString() {
		return MapperUtils.toString(this);
	}
}
