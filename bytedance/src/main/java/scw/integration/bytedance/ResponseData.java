package scw.integration.bytedance;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class ResponseData implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "错误码描述")
	private String description;
	@Schema(description = "错误码", example = "0")
	private Long error_code;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getError_code() {
		return error_code;
	}

	public void setError_code(Long error_code) {
		this.error_code = error_code;
	}
}
