package scw.integration.bytedance.poi;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;

public class Service implements Serializable {
	private static final long serialVersionUID = 1L;
	@Schema(description = "上线状态(1:上线，2:下线)")
	private Long enable;
	@Schema(description = "服务入口拼接参数")
	private ServiceEntry entry;
	@Schema(description = "服务类型(201-预约点餐服务, 202-预约订位服务, 203-排队取号服务, 9001-门票预订服务, 9101-团购套餐服务, 9102-领优惠劵服务, 9201-在线预约服务, 9301-外卖服务, 40-住宿服务, 200-预约券服务)	")
	private Long service_type;

	public Long getEnable() {
		return enable;
	}

	public void setEnable(Long enable) {
		this.enable = enable;
	}

	public ServiceEntry getEntry() {
		return entry;
	}

	public void setEntry(ServiceEntry entry) {
		this.entry = entry;
	}

	public Long getService_type() {
		return service_type;
	}

	public void setService_type(Long service_type) {
		this.service_type = service_type;
	}
}
