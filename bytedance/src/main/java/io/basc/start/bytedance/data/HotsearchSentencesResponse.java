package io.basc.start.bytedance.data;

import java.util.List;

import io.basc.start.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class HotsearchSentencesResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "刷新时间", example = "2019-10-31 11:00:37")
	private String active_time;
	@Schema(description = "实时热点词")
	private List<HotsearchSentences> list;

	public String getActive_time() {
		return active_time;
	}

	public void setActive_time(String active_time) {
		this.active_time = active_time;
	}

	public List<HotsearchSentences> getList() {
		return list;
	}

	public void setList(List<HotsearchSentences> list) {
		this.list = list;
	}
}
