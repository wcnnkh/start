package io.basc.start.bytedance.poi;

import java.util.List;

import io.basc.start.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class MatchResultList<T> extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "匹配的结果")
	private List<T> match_result_list;

	public List<T> getMatch_result_list() {
		return match_result_list;
	}

	public void setMatch_result_list(List<T> match_result_list) {
		this.match_result_list = match_result_list;
	}
}
