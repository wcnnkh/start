package io.basc.start.aliyun.oss;

import static com.aliyun.oss.internal.OSSConstants.DEFAULT_CHARSET_NAME;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.auth.DefaultCredentialProvider;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.PolicyConditions;

import io.basc.framework.codec.support.CharsetCodec;
import io.basc.framework.data.ResourceStorageService;
import io.basc.framework.data.StorageException;
import io.basc.framework.http.HttpRequestEntity;
import io.basc.framework.http.MediaType;
import io.basc.framework.io.IOUtils;
import io.basc.framework.io.Resource;
import io.basc.framework.io.UrlResource;
import io.basc.framework.net.message.InputMessage;
import io.basc.framework.util.CollectionUtils;
import io.basc.framework.validation.FastValidator;

public class AliyunStorage implements ResourceStorageService {
	private final OSSClient oss;
	private final String baseUrl;
	private final String bucketName;

	public AliyunStorage(AliyunOssConfig config) {
		FastValidator.validate(config);
		DefaultCredentialProvider provider = new DefaultCredentialProvider(config.getAccessKeyId(),
				config.getSecretAccessKey());
		ClientConfiguration clientConfiguration = new ClientConfiguration();
		this.oss = new OSSClient(config.getEndpoint(), provider, clientConfiguration);
		this.bucketName = config.getBucketName();
		this.baseUrl = config.getBaseUrl();
	}

	public OSSClient getOss() {
		return oss;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public String getBucketName() {
		return bucketName;
	}

	public AliyunStorage(OSSClient oss, String bucketName, String baseUrl) {
		this.oss = oss;
		this.bucketName = bucketName;
		this.baseUrl = baseUrl;
	}

	@Override
	public Resource get(String key) throws StorageException, IOException {
		return new UrlResource(baseUrl + key);
	}

	@Override
	public boolean put(String key, InputMessage input) throws StorageException, IOException {
		InputStream is = null;
		try {
			is = input.getInputStream();
			oss.putObject(bucketName, key, is);
		} finally {
			IOUtils.close(is);
		}
		return true;
	}

	@Override
	public boolean delete(String key) throws StorageException {
		oss.deleteObject(bucketName, key.startsWith("/") ? key.substring(1) : key);
		return true;
	}

	@Override
	public boolean delete(URI uri) throws StorageException {
		String str = uri.toString();
		if (!str.startsWith(baseUrl)) {
			return false;
		}

		return delete(str.substring(baseUrl.length()));
	}

	@Override
	public List<Resource> list(String prefix, String marker, int limit) throws StorageException, IOException {
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest(bucketName, prefix, marker, null, limit);
		ObjectListing objectListing = oss.listObjects(listObjectsRequest);
		if (objectListing == null) {
			return Collections.emptyList();
		}

		List<com.aliyun.oss.model.OSSObjectSummary> summaries = objectListing.getObjectSummaries();
		if (CollectionUtils.isEmpty(summaries)) {
			return Collections.emptyList();
		}

		List<Resource> resources = new ArrayList<Resource>(summaries.size());
		for (com.aliyun.oss.model.OSSObjectSummary summary : summaries) {
			if (summary == null) {
				continue;
			}

			resources.add(new OssResource(summary));
		}
		return resources;
	}

	private final class OssResource extends UrlResource {
		private final com.aliyun.oss.model.OSSObjectSummary summary;

		public OssResource(com.aliyun.oss.model.OSSObjectSummary summary) throws MalformedURLException {
			super(baseUrl + summary.getKey());
			this.summary = summary;
		}

		@Override
		public long lastModified() throws IOException {
			Date date = summary.getLastModified();
			return date == null ? super.lastModified() : date.getTime();
		}
	}

	/**
	 * web端直传 {@link https://help.aliyun.com/document_detail/31923.html}
	 */
	@Override
	public UploadPolicy generatePolicy(String key, Date expiration) throws StorageException {
		PolicyConditions policyConditions = new PolicyConditions();
		policyConditions.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
		policyConditions.addConditionItem(MatchMode.Exact, PolicyConditions.COND_KEY, key);
		String postPolicy = oss.generatePostPolicy(expiration, policyConditions);
		String sign = oss.calculatePostSignature(postPolicy);
		byte[] binaryData = CharsetCodec.charset(DEFAULT_CHARSET_NAME).encode(DEFAULT_CHARSET_NAME);
		String encPolicy = BinaryUtil.toBase64String(binaryData);
		Map<String, String> parameters = new LinkedHashMap<String, String>();
		parameters.put("key", key);
		// 要求的参数key就是大写
		parameters.put("OSSAccessKeyId", oss.getCredentialsProvider().getCredentials().getAccessKeyId());
		parameters.put("policy", encPolicy);
		parameters.put("Signature", sign);

		HttpRequestEntity<?> requestEntity = HttpRequestEntity.post(oss.getEndpoint())
				.contentType(MediaType.MULTIPART_FORM_DATA).body(parameters);
		return new UploadPolicy(getBaseUrl(), requestEntity);
	}

}
