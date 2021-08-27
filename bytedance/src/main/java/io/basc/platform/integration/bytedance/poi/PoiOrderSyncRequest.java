package io.basc.platform.integration.bytedance.poi;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class PoiOrderSyncRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "发送请求的时间，精确到毫秒", required = true)
	@NotNull
	private Long date_time;
	@Schema(description = "外部商家信息", required = true)
	@NotNull
	private ExtShopInfo ext_shop_info;
	@Schema(description = "小程序形式对接抖音时，该字段必传")
	private MiniApp mini_app;
	@Schema(description = "订单的细节，不同的订单业务有不同的结构体，请具体询问业务方字段结构", required = true)
	@NotNull
	private String order_detail;
	@Schema(description = "订单类型- 201 预约点餐订单, 202 餐厅预定订单, 203 餐厅排队订单, 9001 景区门票订单, 9101 团购券订单, 9201 在线预约订单, 9301 外卖订单, 140 住宿订单, 200 预售券订单", required = true)
	@NotNull
	private Long order_type;

	public Long getDate_time() {
		return date_time;
	}

	public void setDate_time(Long date_time) {
		this.date_time = date_time;
	}

	public ExtShopInfo getExt_shop_info() {
		return ext_shop_info;
	}

	public void setExt_shop_info(ExtShopInfo ext_shop_info) {
		this.ext_shop_info = ext_shop_info;
	}

	public MiniApp getMini_app() {
		return mini_app;
	}

	public void setMini_app(MiniApp mini_app) {
		this.mini_app = mini_app;
	}

	public String getOrder_detail() {
		return order_detail;
	}

	public void setOrder_detail(String order_detail) {
		this.order_detail = order_detail;
	}

	public Long getOrder_type() {
		return order_type;
	}

	public void setOrder_type(Long order_type) {
		this.order_type = order_type;
	}
}
