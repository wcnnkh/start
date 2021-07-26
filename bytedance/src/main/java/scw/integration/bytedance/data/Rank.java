package scw.integration.bytedance.data;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class Rank implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "排名", example = "1")
	private Integer rank;

	public Integer getRank() {
		return rank;
	}

	public void setRank(Integer rank) {
		this.rank = rank;
	}
}
