package io.basc.start.aliyun.oss;

import javax.validation.constraints.NotEmpty;

import io.basc.framework.beans.annotation.ConfigurationProperties;

@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOssConfig {
	@NotEmpty
	private String accessKeyId;
	@NotEmpty
	private String secretAccessKey;
	@NotEmpty
	private String endpoint;
	@NotEmpty
	private String bucketName;
	private String baseUrl;

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getSecretAccessKey() {
		return secretAccessKey;
	}

	public void setSecretAccessKey(String secretAccessKey) {
		this.secretAccessKey = secretAccessKey;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getBaseUrl() {
		return baseUrl == null ? endpoint : baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}
}
