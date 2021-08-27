package io.basc.platform.integration.bytedance.poi;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import io.swagger.v3.oas.annotations.media.Schema;

public class PoiV2SupplierMatchRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "第三方上传的匹配数据", required = true)
	@NotEmpty
	private List<PoiV2SupplierMatch> match_data_list;

	public List<PoiV2SupplierMatch> getMatch_data_list() {
		return match_data_list;
	}

	public void setMatch_data_list(List<PoiV2SupplierMatch> match_data_list) {
		this.match_data_list = match_data_list;
	}
}
