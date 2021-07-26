package scw.integration.bytedance.data;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.oauth.ClientRequest;

public class DataExternalSdkShareRequest extends ClientRequest {
	private static final long serialVersionUID = 1L;
	@Schema(description = "最近30天，开始日期(yyyy-MM-dd)")
	private String begin_date;
	@Schema(description = "最近30天，结束日期(yyyy-MM-dd)")
	private String end_date;

	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
}
