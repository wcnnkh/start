package io.basc.star.aliyun.sms;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import io.basc.framework.codec.encode.HmacMD5;
import io.basc.framework.codec.encode.MD5;
import io.basc.framework.codec.support.CharsetCodec;
import io.basc.framework.context.annotation.Provider;
import io.basc.framework.core.Ordered;
import io.basc.framework.http.HttpUtils;
import io.basc.framework.http.MediaType;
import io.basc.framework.json.JSONUtils;
import io.basc.framework.json.JsonObject;
import io.basc.framework.logger.Logger;
import io.basc.framework.logger.LoggerFactory;
import io.basc.framework.util.Assert;
import io.basc.framework.util.CollectionUtils;
import io.basc.framework.util.Status;
import io.basc.framework.util.StringUtils;
import io.basc.framework.util.TimeUtils;
import io.basc.framework.util.XUtils;

@Provider(order = Ordered.LOWEST_PRECEDENCE)
public class AliDaYuSms implements AliyunSms {
	private static Logger logger = LoggerFactory.getLogger(AliDaYuSms.class);

	private String host = "http://gw.api.taobao.com/router/rest";
	private String version = "2.0";
	private String format = "json";
	private String signMethod = "md5";

	private final String appKey;
	private final String appSecret;

	public AliDaYuSms(String appKey, String appSecret) {
		this.appKey = appKey;
		this.appSecret = appSecret;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		Assert.requiredArgument(StringUtils.hasText(format), "format");
		this.host = host;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		Assert.requiredArgument(StringUtils.hasText(format), "format");
		this.version = version;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		Assert.requiredArgument(StringUtils.hasText(format), "format");
		this.format = format;
	}

	public String getSignMethod() {
		return signMethod;
	}

	public void setSignMethod(String signMethod) {
		Assert.requiredArgument(StringUtils.hasText(format), "format");
		this.signMethod = signMethod;
	}

	public String getAppKey() {
		return appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	@Override
	public Status<String> send(MessageModel messageModel,
			Map<String, ?> parameterMap, Collection<String> phones) {
		Assert.requiredArgument(messageModel != null, "messageModel");
		Assert.requiredArgument(!CollectionUtils.isEmpty(phones), "phones");
		Map<String, String> map = new HashMap<String, String>();
		map.put("app_key", appKey);
		map.put("v", version);
		map.put("format", format);
		map.put("sign_method", getSignMethod());

		map.put("timestamp", TimeUtils.format(System.currentTimeMillis(),
				"yyyy-MM-dd HH:mm:ss"));
		map.put("sms_free_sign_name", messageModel.getSms_free_sign_name());
		if (!CollectionUtils.isEmpty(parameterMap)) {
			map.put("sms_param",
					JSONUtils.getJsonSupport().toJSONString(parameterMap));
		}
		map.put("sms_template_code", messageModel.getSms_template_code());
		map.put("method", "alibaba.aliqin.fc.sms.num.send");
		map.put("sms_type", "normal");
		map.put("rec_num", StringUtils.collectionToCommaDelimitedString(phones));
		map.put("sign", getSign(map));
		JsonObject response = HttpUtils
				.getHttpClient()
				.post(JsonObject.class, host, map,
						MediaType.APPLICATION_FORM_URLENCODED).getBody();
		if (response.containsKey("alibaba_aliqin_fc_sms_num_send_response")) {
			response = response
					.getJsonObject("alibaba_aliqin_fc_sms_num_send_response");
		}

		response = response.getJsonObject("result");
		if (response.containsKey("err_code")
				&& response.getIntValue("err_code") == 0) {
			return XUtils.status(true, response.toJSONString());
		}

		logger.error(response.toString());
		JsonObject errorResponse = response.getJsonObject("error_response");
		String msg = errorResponse.getString("sub_msg");
		if (StringUtils.isEmpty(msg)) {
			msg = errorResponse.getString("msg");
		}
		return XUtils.status(false, msg);
	}

	/**
	 * 签名
	 * 
	 * @param map
	 * @param secret
	 * @param sign_method
	 * @return
	 */
	protected String getSign(Map<String, String> map) {
		String[] keys = map.keySet().toArray(new String[0]);
		Arrays.sort(keys);
		StringBuilder sb = new StringBuilder();
		boolean isMd5 = false;
		if ("md5".equalsIgnoreCase(getSignMethod())) {
			sb.append(appSecret);
			isMd5 = true;
		}

		for (String key : keys) {
			String value = map.get(key);
			if (StringUtils.isEmpty(key, value)) {
				continue;
			}
			// sb.append(key).append(Http.encode(value, "utf-8"));
			sb.append(key).append(value);
		}

		String bytes = null;
		if (isMd5) {
			sb.append(appSecret);
			bytes = MD5.DEFAULT.fromEncoder(CharsetCodec.UTF_8).encode(
					sb.toString());
		} else {
			bytes = new HmacMD5(CharsetCodec.UTF_8.encode(appSecret)).toHex()
					.fromEncoder(CharsetCodec.UTF_8).encode(sb.toString());
		}

		return bytes.toUpperCase();
	}
}
