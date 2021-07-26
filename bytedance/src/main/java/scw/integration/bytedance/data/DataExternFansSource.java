package scw.integration.bytedance.data;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class DataExternFansSource implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "粉丝来源", example = "来源key")
	private String source;
	@Schema(description = "来源占比，格式:XX.XX% 精确小数点后2位", example = "31.32%")
	private String percent;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}
}
