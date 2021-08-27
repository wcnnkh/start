package io.basc.platform.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class PoiExtPresaleGrouponOrderCreateRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "", required = true)
	@NotNull
	private Long total_price;
	@Schema(description = "达人分佣率，万分数（100表示抽佣万分之一百，即1%）")
	private Long cps_take_rate;
	@Schema(description = "带货达人抖音号")
	private String cps_user_short_id;
	@Schema(description = "购买数量", required = true)
	@NotNull
	private Long num;
	@Schema(description = "抖音用户唯一标识", required = true)
	@NotNull
	private String open_id;
	@Schema(description = "抖音订单号")
	private String order_id;
	@Schema(description = "接入方商品id", required = true)
	@NotNull
	private String spu_ext_id;

	public Long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Long total_price) {
		this.total_price = total_price;
	}

	public Long getCps_take_rate() {
		return cps_take_rate;
	}

	public void setCps_take_rate(Long cps_take_rate) {
		this.cps_take_rate = cps_take_rate;
	}

	public String getCps_user_short_id() {
		return cps_user_short_id;
	}

	public void setCps_user_short_id(String cps_user_short_id) {
		this.cps_user_short_id = cps_user_short_id;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

	public String getOpen_id() {
		return open_id;
	}

	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getSpu_ext_id() {
		return spu_ext_id;
	}

	public void setSpu_ext_id(String spu_ext_id) {
		this.spu_ext_id = spu_ext_id;
	}
}
