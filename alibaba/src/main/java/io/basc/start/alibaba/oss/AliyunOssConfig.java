package io.basc.start.alibaba.oss;

import io.basc.framework.beans.annotation.ConfigurationProperties;
import io.basc.framework.util.StringUtils;
import io.basc.framework.util.Verify;

@ConfigurationProperties(prefix = "aliyun.oss")
public class AliyunOssConfig implements Verify {
	private String accessKeyId;
	private String secretAccessKey;
	private String endpoint;
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

	@Override
	public boolean isVerified() {
		return StringUtils.isNotEmpty(accessKeyId, secretAccessKey, endpoint,
				bucketName);
	}
}
