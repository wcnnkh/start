package io.basc.start.bytedance;

import io.swagger.v3.oas.annotations.media.Schema;

public class Response<D> extends ResponseCode {
	private static final long serialVersionUID = 1L;
	private D data;
	private ResponseExtra extra;
	@Schema(example = "success")
	private String message;

	public D getData() {
		return data;
	}

	public void setData(D data) {
		this.data = data;
	}

	public ResponseExtra getExtra() {
		return extra;
	}

	public void setExtra(ResponseExtra extra) {
		this.extra = extra;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		if (!super.isSuccess()) {
			return false;
		}

		if (extra != null && !extra.isSuccess()) {
			return false;
		}

		if (data != null && data instanceof ResponseCode) {
			if (!((ResponseCode) data).isSuccess()) {
				return false;
			}
		}
		return true;
	}
}
