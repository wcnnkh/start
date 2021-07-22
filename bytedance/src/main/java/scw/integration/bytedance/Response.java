package scw.integration.bytedance;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class Response<D> implements Serializable {
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
		if (extra != null && extra.getError_code() != null && extra.getError_code() != 0) {
			return false;
		}

		if (data != null && data instanceof ResponseCode) {
			ResponseCode responseCode = (ResponseCode) data;
			if (responseCode.getError_code() != null && responseCode.getError_code() != 0) {
				return false;
			}
		}
		return true;
	}
}
