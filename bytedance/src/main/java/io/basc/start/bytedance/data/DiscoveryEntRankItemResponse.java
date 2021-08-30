package io.basc.start.bytedance.data;

import java.util.List;

import io.basc.start.bytedance.ResponseCode;
import io.swagger.v3.oas.annotations.media.Schema;

public class DiscoveryEntRankItemResponse extends ResponseCode {
	private static final long serialVersionUID = 1L;
	@Schema(description = "生成时间", example = "2020-03-31 12:00:00")
	private String active_time;
	private List<Album> list;

	public String getActive_time() {
		return active_time;
	}

	public void setActive_time(String active_time) {
		this.active_time = active_time;
	}

	public List<Album> getList() {
		return list;
	}

	public void setList(List<Album> list) {
		this.list = list;
	}
}
