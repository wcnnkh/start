package scw.integration.bytedance.enterprise;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import scw.integration.bytedance.user.UserPagingRequest;

public class EnterpriseLeadsUserListRequest extends UserPagingRequest {
	private static final long serialVersionUID = 1L;
	@Schema(required = true)
	@NotNull
	private Long start_time;
	@Schema(required = true)
	@NotNull
	private Long end_time;
	@Schema(description = "用户状态: * `-1` - 没兴趣 * `0` - 了解 * `1` - 有兴趣 * `2` - 有意愿 * `10` - 已转化")
	private Long leads_level;
	@Schema(description = "分类: * `0` - 全部 * `1` - 私信互动 * `2` - 组件互动 * `3` - 主页互动 * `4` - 行业锚点互动 * `5` - 直播互动 * `6` - 视频互动	", required = true)
	@NotNull
	private Long action_type;

	public Long getStart_time() {
		return start_time;
	}

	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	public Long getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Long end_time) {
		this.end_time = end_time;
	}

	public Long getLeads_level() {
		return leads_level;
	}

	public void setLeads_level(Long leads_level) {
		this.leads_level = leads_level;
	}

	public Long getAction_type() {
		return action_type;
	}

	public void setAction_type(Long action_type) {
		this.action_type = action_type;
	}

}
