package scw.integration.bytedance.poi;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;

public class PoiExtHotelOrderStatusRequest implements Serializable{
	private static final long serialVersionUID = 1L;
	@Schema(description = "接入方商铺ID", required = true)
	@NotNull
	private String supplier_ext_id;
	@Schema(description = "接入方订单号", required = true)
	@NotNull
	private String order_ext_id;
	@Schema(description = "抖音订单号", required = true)
	@NotNull
	private String order_id;
	@Schema(description = "支付状态, 1 - 付款成功;", required = true)
	@NotNull
	private Long status;

	public String getSupplier_ext_id() {
		return supplier_ext_id;
	}

	public void setSupplier_ext_id(String supplier_ext_id) {
		this.supplier_ext_id = supplier_ext_id;
	}

	public String getOrder_ext_id() {
		return order_ext_id;
	}

	public void setOrder_ext_id(String order_ext_id) {
		this.order_ext_id = order_ext_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

}
