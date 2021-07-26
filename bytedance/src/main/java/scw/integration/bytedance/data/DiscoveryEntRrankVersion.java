package scw.integration.bytedance.data;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class DiscoveryEntRrankVersion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "榜单生成时间", example = "2020-03-30 12:00:00")
	private String active_time;
	@Schema(description = "榜单结束时间", example = "2020-03-30 00:00:00")
	private String end_time;
	@Schema(description = "榜单起始时间", example = "2020-03-23 00:00:00")
	private String start_time;
	@Schema(description = "类型：1=电影 2=电视剧 3=综艺", example = "1")
	private Integer type;
	@Schema(description = "榜单版本", example = "18")
	private Integer version;

	public String getActive_time() {
		return active_time;
	}

	public void setActive_time(String active_time) {
		this.active_time = active_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
