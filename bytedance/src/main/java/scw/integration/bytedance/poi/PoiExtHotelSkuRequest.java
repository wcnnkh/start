package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

public class PoiExtHotelSkuRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "接入方SPU ID 列表", example = "[y0001 y0002]", required = true)
	private List<String> spu_ext_id;
	@Schema(description = "拉取价格时间区间[start_date, end_date)", example = "20191001", required = true)
	private String start_date;
	@Schema(description = "拉取价格时间区间[start_date, end_date)", example = "20191007", required = true)
	private String end_date;

	public List<String> getSpu_ext_id() {
		return spu_ext_id;
	}

	public void setSpu_ext_id(List<String> spu_ext_id) {
		this.spu_ext_id = spu_ext_id;
	}

	public String getStart_date() {
		return start_date;
	}

	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	public String getEnd_date() {
		return end_date;
	}

	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
}
