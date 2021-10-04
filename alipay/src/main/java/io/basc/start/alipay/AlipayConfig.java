package io.basc.start.alipay;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import io.basc.framework.beans.annotation.ConfigurationProperties;
import io.basc.framework.mapper.MapperUtils;

@ConfigurationProperties(prefix="alipay")
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

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}
	
	@Override
	public String toString() {
		return MapperUtils.toString(this);
	}
}
