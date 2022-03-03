package io.basc.start.tencent.wx.api;

import io.basc.framework.codec.encode.SHA1;
import io.basc.framework.codec.support.CharsetCodec;
import io.basc.framework.util.Assert;
import io.basc.framework.util.RandomUtils;
import io.basc.framework.util.StringUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class JsApiSignature implements Serializable {
	private static final long serialVersionUID = 1L;
	private String nonceStr;// 注意 这个随机字符串的S在前端是大写的，可是在签名的时候是小写的
	private long timestamp;// 单位：秒
	private String url;
	private String jsapi_ticket;

	public JsApiSignature() {
		this.nonceStr = RandomUtils.randomString(10);
		this.timestamp = System.currentTimeMillis() / 1000;
	}

	public JsApiSignature(String jsapi_ticket, String url) {
		this();
		this.jsapi_ticket = jsapi_ticket;
		this.url = url;
	}

	public JsApiSignature(String nonceStr, String jsapi_ticket, long timestamp, String url) {
		Assert.isTrue(StringUtils.isNotEmpty(nonceStr, jsapi_ticket, url));
		this.jsapi_ticket = jsapi_ticket;
		this.nonceStr = nonceStr;
		this.timestamp = timestamp;
		this.url = url;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public long getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp 秒
	 */
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getJsapi_ticket() {
		return jsapi_ticket;
	}

	public void setJsapi_ticket(String jsapi_ticket) {
		this.jsapi_ticket = jsapi_ticket;
	}

	public String getSignature() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("noncestr", nonceStr);
		map.put("timestamp", timestamp + "");
		map.put("url", url);
		map.put("jsapi_ticket", jsapi_ticket);

		String[] keys = map.keySet().toArray(new String[0]);
		Arrays.sort(keys);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.length; i++) {
			String key = keys[i];
			sb.append(key).append("=").append(map.get(key));
			if (i < keys.length - 1) {
				sb.append("&");
			}
		}
		return CharsetCodec.UTF_8.toEncoder(SHA1.DEFAULT).encode(sb.toString());
	}
}
