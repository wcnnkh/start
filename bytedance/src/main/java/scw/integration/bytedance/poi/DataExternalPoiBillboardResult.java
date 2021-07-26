package scw.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.data.Rank;

public class DataExternalPoiBillboardResult extends Rank {
	private static final long serialVersionUID = 1L;
	@Schema(description = "poi名称")
	private String poi_name;
	@Schema(description = "得分")
	private String score;
	@Schema(description = "poi id")
	private String poi_id;

	public String getPoi_name() {
		return poi_name;
	}

	public void setPoi_name(String poi_name) {
		this.poi_name = poi_name;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getPoi_id() {
		return poi_id;
	}

	public void setPoi_id(String poi_id) {
		this.poi_id = poi_id;
	}

}
