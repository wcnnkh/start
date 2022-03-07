package io.basc.start.tencent.wx.api;

import io.basc.start.tencent.wx.WeiXinException;
import lombok.Getter;

@Getter
public class WeiXinApiException extends WeiXinException {
	private static final long serialVersionUID = 1L;
	private final long errcode;
	private final String errmsg;

	public WeiXinApiException(long errcode, String errmsg) {
		super(errmsg);
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

	/**
	 * 获取 access_token 时 AppSecret 错误，或者 access_token 无效
	 * 
	 * @return
	 */
	public boolean isInvalidCredential() {
		return errcode == 40001;
	}
}
