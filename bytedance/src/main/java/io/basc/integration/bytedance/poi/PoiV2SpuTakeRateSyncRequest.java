package io.basc.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class PoiV2SpuTakeRateSyncRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "抖音号，抖音个人页可查询", required = true)
	@NotNull
	private String douyin_id;
	@Schema(description = "接入方商品ID", required = true)
	@NotNull
	private String spu_ext_id;
	@Schema(description = "1-在线； 2-下线", required = true)
	@NotNull
	private Long status;
	@Schema(description = "分佣值，万分位。 比如800=8%", required = true)
	@NotNull
	private Long take_rate;

	public String getDouyin_id() {
		return douyin_id;
	}

	public void setDouyin_id(String douyin_id) {
		this.douyin_id = douyin_id;
	}

	public String getSpu_ext_id() {
		return spu_ext_id;
	}

	public void setSpu_ext_id(String spu_ext_id) {
		this.spu_ext_id = spu_ext_id;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getTake_rate() {
		return take_rate;
	}

	public void setTake_rate(Long take_rate) {
		this.take_rate = take_rate;
	}

}
