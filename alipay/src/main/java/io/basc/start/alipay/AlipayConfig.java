package io.basc.start.alipay;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import io.basc.framework.beans.annotation.ConfigurationProperties;
import lombok.Data;

@ConfigurationProperties(prefix="alipay")
@Data
public class AlipayConfig implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotEmpty
	private String publicKey;
	@NotEmpty
	private String appId;
	@NotEmpty
	private String dataType = "json";// json
	@NotEmpty
	private String charset = "UTF-8";
	@NotEmpty
	private String signType = "RSA2";// RSA2
	@NotEmpty
	private String privateKey;
}
