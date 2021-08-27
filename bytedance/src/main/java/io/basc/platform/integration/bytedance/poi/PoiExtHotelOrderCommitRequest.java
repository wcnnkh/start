package io.basc.platform.integration.bytedance.poi;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

public class PoiExtHotelOrderCommitRequest implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "预订人电话", required = true)
	@NotNull
	private String customer_phone;
	@Schema(description = "预定数量", required = true)
	@NotNull
	private Long reserve_amount;
	@Schema(description = "接入方房型ID", required = true)
	@NotNull
	private String spu_ext_id;
	@Schema(description = "订单支付状态。0 - 未支付, 1 - 已支付", required = true)
	@NotNull
	private Long status;
	@Schema(description = "总价, 单位人民币分", required = true)
	@NotNull
	private Long total_price;
	@Schema(description = "入住时间 yyyyMMdd", required = true)
	@NotNull
	private Long check_in;
	@Schema(description = "离店时间 yyyyMMdd", required = true)
	@NotNull
	private String check_out;
	private DatePrice[] date_price;
	@Schema(description = "抖音订单号", required = true)
	@NotNull
	private String order_id;
	@Schema(description = "备注")
	private String remark;
	@Schema(description = "入住人列表")
	private List<Customer> customer_list;
	@Schema(description = "预订人姓名", required = true)
	@NotNull
	private String customer_name;

	public String getCustomer_phone() {
		return customer_phone;
	}

	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}

	public Long getReserve_amount() {
		return reserve_amount;
	}

	public void setReserve_amount(Long reserve_amount) {
		this.reserve_amount = reserve_amount;
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

	public Long getTotal_price() {
		return total_price;
	}

	public void setTotal_price(Long total_price) {
		this.total_price = total_price;
	}

	public Long getCheck_in() {
		return check_in;
	}

	public void setCheck_in(Long check_in) {
		this.check_in = check_in;
	}

	public String getCheck_out() {
		return check_out;
	}

	public void setCheck_out(String check_out) {
		this.check_out = check_out;
	}

	public DatePrice[] getDate_price() {
		return date_price;
	}

	public void setDate_price(DatePrice[] date_price) {
		this.date_price = date_price;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<Customer> getCustomer_list() {
		return customer_list;
	}

	public void setCustomer_list(List<Customer> customer_list) {
		this.customer_list = customer_list;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public static class Customer implements Serializable{
		private static final long serialVersionUID = 1L;
		@Schema(description = "中文全称", example = "韩梅梅")
		private String cn_name;
		@Schema(description = "英文名", example = "kobe")
		private String given_name;
		@Schema(description = "英文姓", example = "bryant")
		private String surname;
		public String getCn_name() {
			return cn_name;
		}
		public void setCn_name(String cn_name) {
			this.cn_name = cn_name;
		}
		public String getGiven_name() {
			return given_name;
		}
		public void setGiven_name(String given_name) {
			this.given_name = given_name;
		}
		public String getSurname() {
			return surname;
		}
		public void setSurname(String surname) {
			this.surname = surname;
		}
	}
}
