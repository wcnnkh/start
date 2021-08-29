package io.basc.integration.bytedance;

public class ResponseSubCode extends ResponseCode {
	private static final long serialVersionUID = 1L;
	private Long sub_error_code;
	private String sub_description;

	public Long getSub_error_code() {
		return sub_error_code;
	}

	public void setSub_error_code(Long sub_error_code) {
		this.sub_error_code = sub_error_code;
	}

	public String getSub_description() {
		return sub_description;
	}

	public void setSub_description(String sub_description) {
		this.sub_description = sub_description;
	}

	@Override
	public boolean isSuccess() {
		if (!super.isSuccess()) {
			return false;
		}

		if (sub_error_code != null && sub_error_code != 0) {
			return false;
		}
		return true;
	}
}
